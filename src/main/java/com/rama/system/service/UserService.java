package com.rama.system.service;



import com.rama.system.model.User;

public interface UserService {
	public boolean checkEmail(String email);
	public User createUser(User user, String url);
	public boolean verifyAccount(String code);
	
	
	
	

}