package com.payroll.usermanagement.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll.usermanagement.entities.User;
import com.payroll.usermanagement.entities.Userrole;
import com.payroll.usermanagement.repositories.UserRepository;

@Service
public class UserCredentialsandRolesServiceImpl implements UserCredentialsandRolesService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public Set<Userrole> verifyLoginRecords(String email, String password) {
		Optional<User> userRecord = userRepository.getUserByEmailandPassword(email, password);
		if(userRecord.isEmpty()) {
			//custom failure login message
		}
		return userRecord.get().getUserroles();
	}

}
