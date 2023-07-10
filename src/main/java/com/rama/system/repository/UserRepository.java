package com.rama.system.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.rama.system.model.Admin;

@Repository
public interface UserRepository extends JpaRepository<Admin, Integer>{
	
	public boolean existsByAdminEmail(String email);
	public Admin findByAdminEmail(String email);
	public Admin findByVerificationCode(String code);

}