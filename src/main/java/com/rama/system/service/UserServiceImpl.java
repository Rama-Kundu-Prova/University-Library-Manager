package com.rama.system.service;


import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rama.system.model.User;
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
		return userRepo.existsByUserEmail(email);
	}

	@Override
	public User createUser(User user, String url) {
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		user.setRole("ROLE_USER");
		user.setEnable(false);
		
		
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
	   
	    
		user.setVerificationCode(rn);
		
		sendVerificationMail(user, url);
		return userRepo.save(user);
	}
	private void sendVerificationMail(User user, String url)
	{
	
		String from ="eclubsite27@gmail.com";
		String to=user.getUserEmail();
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
			content=content.replace("[[name]]", user.getUserName());
			String siteUrl="Http://localhost:8080"+"/verify?code="+user.getVerificationCode();

			content =content.replace("[[URL]]", siteUrl);
			helper.setText(content,true);
			mailSender.send(message);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	public boolean verifyAccount(String code) {
	    
		User user= userRepo.findByVerificationCode(code);
		if(user!=null)
		{
			user.setEnable(true);
			user.setVerificationCode(null);
			userRepo.save(user);
			return true;
		}
		return false;
	}


	
	

}