package com.payroll.usermanagement.services;

import java.util.List;

import com.payroll.usermanagement.entities.Userrole;

public interface UserCredentialsandRolesService {

	public List<Userrole> verifyLoginRecords(String email, String password);
}
