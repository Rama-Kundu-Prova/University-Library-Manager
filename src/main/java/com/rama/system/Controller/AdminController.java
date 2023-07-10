package com.rama.system.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rama.system.model.Admin;
import com.rama.system.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	
	@Autowired
	private UserRepository userRepo;
	
	@ModelAttribute
	private void userDetails(Model m, Principal p) {
		String email = p.getName();
		Admin admin = userRepo.findByAdminEmail(email);
		m.addAttribute("admin", admin);

	}
	
	@GetMapping("/")
	public String home() {
		return "admin/home";
	}

}
