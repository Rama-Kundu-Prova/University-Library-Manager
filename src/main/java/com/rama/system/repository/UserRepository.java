package com.rama.system.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.rama.system.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public boolean existsByUserEmail(String email);
	public User findByUserEmail(String email);
	public User findByVerificationCode(String code);

}