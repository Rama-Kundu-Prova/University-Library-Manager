package com.rama.system.Controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rama.system.model.Admin;
import com.rama.system.repository.UserRepository;
import com.rama.system.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@ModelAttribute
	private void userDetails(Model m, Principal p) {

		if (p != null) {
			String email = p.getName();
			Admin admin = userRepository.findByAdminEmail(email);
			m.addAttribute("admin", admin);
		}

	}

	

	@GetMapping("/signin")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}
	@PostMapping("/createUser")
	public String createUser(@ModelAttribute Admin admin,HttpServletRequest request) {

		
		 String url=request.getRequestURI().toString();
		 url=url.replace(request.getServletPath(), "");
		 
		boolean f = userService.checkEmail(admin.getAdminEmail());
		int ok=0;
		
		if (f) {
			// session.setAttribute("msg", "Email Id alreday exists");
			ok=1;
			
		}
		else
		{
			Admin admininfo = userService.createUser(admin,url);
			

			/*if (userInfo != null) {
				session.setAttribute("msg", "Register Sucessfully");
			} else {
				session.setAttribute("msg", "Something wrong on server");
			}*/
			
		}
		if(ok==1)
		{
			System.out.println("hereeeeeeeeeeeeeeeeeeeee");
			return "redirect:/register";
		}
		else
		{
			return "login";
		}
	

		

		
	}
	@GetMapping("/verify")
	public String verifyAccount(@Param("code") String code)
	{
		if(userService.verifyAccount(code))
		{
			
			return "login";
		}
		else
		{
			
			return "redirect:/register";
		}
		
	}

	
    

}