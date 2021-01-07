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

@Component
public class PayrollUsersDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findUserByNameSingle(username);
		
		if(user.isEmpty()) {
			throw new UsernameNotFoundException("User with username " + username + "doesn't exist");
		}
		
		//create a granted authority based on user's role
		//can't pass null authorities to user. Hence initialize with an empty arraylist
		List<GrantedAuthority> authorities = new ArrayList<>();
		if(user.get().getEnabled() == true) {
			Set<Userrole> userroles =  user.get().getUserroles();
			for(Userrole u: userroles) {
				authorities = AuthorityUtils.createAuthorityList(u.getRole().getRolename());
			}
		}
		
		//create a UserDetails object from the data
		UserDetails userDetails = new org.springframework.security.core.userdetails.User
				(user.get().getName(),user.get().getPassword().toString(),authorities);
		
		return userDetails;
	}

}
