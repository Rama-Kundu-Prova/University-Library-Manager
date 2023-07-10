package com.rama.system.service;



import com.rama.system.model.Admin;

public interface UserService {
	public boolean checkEmail(String email);
	public Admin createUser(Admin admin, String url);
	public boolean verifyAccount(String code);
	
	
	
	

}