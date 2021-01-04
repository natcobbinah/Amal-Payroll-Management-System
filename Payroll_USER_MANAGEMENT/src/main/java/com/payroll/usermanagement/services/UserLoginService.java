package com.payroll.usermanagement.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.payroll.usermanagement.entities.User;
import com.payroll.usermanagement.entities.Userrole;

public class UserLoginService implements UserDetails {
	
	@Autowired
	User user;

	public UserLoginService(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Userrole> userroles = user.getUserroles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for(Userrole urole : userroles) {
			authorities.add(new SimpleGrantedAuthority(urole.getRole().getRolename()));
		}
		return authorities;
	}
	
	public String getEmail() {
		return user.getEmail();
	}

	@Override
	public String getPassword() {
		return  user.getPassword().toString();
	}
	
	public byte[] getUserPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		boolean enabled = user.getEnabled();
		if(!enabled) {
			return false;
		}
		return user.getEnabled();
	}

}
