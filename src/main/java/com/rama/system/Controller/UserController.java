package com.rama.system.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rama.system.model.User;
import com.rama.system.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserRepository userRepo;
	
	@ModelAttribute
	private void userDetails(Model m, Principal p) {
		String email = p.getName();
		User user = userRepo.findByUserEmail(email);
		m.addAttribute("user", user);

	}
	
	@GetMapping("/home")
	public String home() {
		return "user/home";
	}
	@GetMapping("/changePassword")
	public String loadChangePassword()
	{
		return "user/change_password";
	}
	
	@GetMapping("/updateProfile")
	public String loadUpdateProfile()
	{
		return "user/update_profile";
	}
	@GetMapping("/profile")
	public String loadProfile()
	{
		return "user/profile";
	}
	
	@GetMapping("/register_book")
	public String register_book() 
	{
		return "user/register_book";
	}

}
