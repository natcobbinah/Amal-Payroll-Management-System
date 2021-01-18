package com.payroll.usermanagement.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.payroll.usermanagement.entities.User;
import com.payroll.usermanagement.entities.Userrole;
import com.payroll.usermanagement.repositories.UserRepository;
import com.payroll.usermanagement.repositories.UserroleRepository;

@Component
public class PayrollUsersDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserroleRepository userroleRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.getUserByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("User with email " + email + "doesn't exist");
		}

		// create a granted authority based on user's role
		// can't pass null authorities to user. Hence initialize with an empty arraylist
		List<GrantedAuthority> authorities = new ArrayList<>();
		List<Userrole> userrole = userroleRepository.getUsersByRole(user.getId()); //get userroles by id from user object
		for (Userrole u : userrole) {
			authorities = AuthorityUtils.createAuthorityList(u.getRole().getRolename());
			System.out.println(authorities);
		}

		// create a UserDetails object from the data
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getName(),
				user.getPassword(), authorities);

		return userDetails;
	}
}
