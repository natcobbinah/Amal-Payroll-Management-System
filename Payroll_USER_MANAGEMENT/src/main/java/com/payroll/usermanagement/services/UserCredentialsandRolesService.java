package com.payroll.usermanagement.services;

import java.util.Set;

import com.payroll.usermanagement.entities.Userrole;

public interface UserCredentialsandRolesService {

	public Set<Userrole> verifyLoginRecords(String email, String password);
}
