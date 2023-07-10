package com.rama.system.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rama.system.model.Admin;
import com.rama.system.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Admin admin=userRepo.findByAdminEmail(email);
		
		if(admin!=null) {
			return new CustomUserDetails(admin);
		}
		throw new UsernameNotFoundException("user not available");
	}
	
	
	

}