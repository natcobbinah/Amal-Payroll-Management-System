package com.payroll.usermanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.payroll.usermanagement.entities.User;
import com.payroll.usermanagement.repositories.UserRepository;

public class UserLoginServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}

	public UserDetails loadUserByEmail(String email) {
		User user = userRepository.getUserByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("Could not find user with specified email");
		}
		return new UserLoginService(user);
	}
}
