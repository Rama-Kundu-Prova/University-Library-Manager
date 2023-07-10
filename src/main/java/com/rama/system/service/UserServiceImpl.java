package com.rama.system.service;


import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rama.system.model.Admin;
import com.rama.system.repository.UserRepository;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JavaMailSender mailSender;
	@Override
	public boolean checkEmail(String email) {
		return userRepo.existsByAdminEmail(email);
	}

	@Override
	public Admin createUser(Admin admin, String url) {
		admin.setAdminPassword(passwordEncoder.encode(admin.getAdminPassword()));
		admin.setRole("ROLE_ADMIN");
		admin.setEnable(false);
		
		
	    String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
	    String numbers = "0123456789";
	    String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
	    StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    int length = 65;

	    for(int i = 0; i < length; i++) {
	      int index = random.nextInt(alphaNumeric.length());

	      char randomChar = alphaNumeric.charAt(index);
	      sb.append(randomChar);
	    }

	    String rn = sb.toString();
	   
	    
		admin.setVerificationCode(rn);
		
		sendVerificationMail(admin, url);
		return userRepo.save(admin);
	}
	private void sendVerificationMail(Admin admin, String url)
	{
	
		String from ="eclubsite27@gmail.com";
		String to=admin.getAdminEmail();
		String subject="Account Verification";
		String content="Dear [[name]],<br>"
				+ "Please click the link bellow to verify your registration:<br>"
				+ "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
				+ "Thank you,<br>"
				+"University-Library-Manager";
		
		try {
			
			MimeMessage message=mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(message);
			
			helper.setFrom(from,"E-Club");
			helper.setTo(to);
			helper.setSubject(subject);
			content=content.replace("[[name]]", admin.getAdminName());
			String siteUrl="Http://localhost:8080"+"/verify?code="+admin.getVerificationCode();

			content =content.replace("[[URL]]", siteUrl);
			helper.setText(content,true);
			mailSender.send(message);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	public boolean verifyAccount(String code) {
	    
		Admin admin= userRepo.findByVerificationCode(code);
		if(admin!=null)
		{
			admin.setEnable(true);
			admin.setVerificationCode(null);
			userRepo.save(admin);
			return true;
		}
		return false;
	}


	
	

}