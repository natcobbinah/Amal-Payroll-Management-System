package com.payroll.usermanagement.passwordEncoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	public static BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
