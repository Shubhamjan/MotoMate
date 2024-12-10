package com.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.demo.config.CustomUser;
import com.demo.entities.User;
import com.demo.repository.UserRepository;



@Component
public class CustomUserDetailsService implements UserDetailsService{
	//When a class is annotated with @Component, 
//	Spring's component-scanning mechanism can pick it up and register it as a bean within the application context
//	, making it eligible for dependency injection.
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User e=userRepo.findByEmail(email);
		
		if(e==null) {
			throw new UsernameNotFoundException("Not found");

		}else {
			return new CustomUser(e);
		}
//		return null;
	}

}
