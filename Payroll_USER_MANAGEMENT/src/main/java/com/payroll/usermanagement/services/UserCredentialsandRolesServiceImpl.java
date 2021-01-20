package com.payroll.usermanagement.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException;
import org.springframework.stereotype.Service;

import com.payroll.usermanagement.entities.User;
import com.payroll.usermanagement.entities.Userrole;
import com.payroll.usermanagement.repositories.UserRepository;
import com.payroll.usermanagement.repositories.UserroleRepository;

@Service
public class UserCredentialsandRolesServiceImpl implements UserCredentialsandRolesService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserroleRepository userroleRepository;
	
	@Override
	public List<Userrole> verifyLoginRecords(String email, String password) {
		Optional<User> user = userRepository.getUserByEmailandPassword(email, password);
		
		List<Userrole> userrole = userroleRepository.getUsersByRole(user.get().getId());
		if(user.isEmpty()) {
			throw new UnapprovedClientAuthenticationException("user not found");
		}
		
		return userrole;
	}

}
