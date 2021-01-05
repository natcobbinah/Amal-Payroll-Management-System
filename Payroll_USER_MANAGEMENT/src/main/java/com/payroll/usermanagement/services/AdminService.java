package com.payroll.usermanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.payroll.usermanagement.entities.Department;
import com.payroll.usermanagement.entities.Role;
import com.payroll.usermanagement.entities.User;
import com.payroll.usermanagement.entities.Userdepartment;
import com.payroll.usermanagement.entities.Userrole;

public interface AdminService {

	User addUser(User user);
	
	List<User> getAllUsers();
	
	Optional<User> findUserbyId(int userid);
	
	ResponseEntity<?> deleteUserbyId(int userid);
	
	List<User> deleteUsersbyId(List<String> values);
	
	List<User> disableUsers(List<String> employeeid);
	
	User findUserByEmployeeid(String employeeid);
	
	List<User> findUserByName(String name);
	
	User findUserByEmail(String email);
	
	User disableUser(String employeeid);
	
	List<Userrole> getUsersbyRole(int roleid);
	
	List<Userdepartment> getUserbyDepartment(int departmentid);
	
	Role addRole(Role role);
	
	List<Role> getAllRoles();
	
	Optional<Role> findRoleById(int roleid);
	
	String deleteRole(int roleid);
	
	Department addDepartment(Department department);
	
	List<Department> getAllDepartments();
	
	Optional<Department> findDepartmentbyId(int deptid);
	
	String deleteDepartment(int departmenid);
	
}
