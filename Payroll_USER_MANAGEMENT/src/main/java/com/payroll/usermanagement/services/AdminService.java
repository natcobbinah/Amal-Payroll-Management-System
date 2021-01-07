package com.payroll.usermanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.payroll.usermanagement.entities.Department;
import com.payroll.usermanagement.entities.Role;
import com.payroll.usermanagement.entities.User;
import com.payroll.usermanagement.entities.Userdepartment;
import com.payroll.usermanagement.entities.Userrole;

public interface AdminService {

	User addUser(User user);
	
	Iterable<User> getAllUsers(Pageable pageable);
	
	Optional<User> findUserbyId(int userid);
	
	ResponseEntity<?> deleteUserbyId(int userid);
	
	Iterable<User> deleteUsersbyId(List<String> values);
	
	Iterable<User> disableUsers(List<String> employeeid);
	
	User findUserByEmployeeid(String employeeid);
	
	List<User> findUserByName(String name);
	
	User findUserByEmail(String email);
	
	User disableUser(String employeeid);
	
	List<Userrole> getUsersbyRole(int roleid);
	
	List<Userdepartment> getUserbyDepartment(int departmentid);
	
	Role addRole(Role role);
	
	Iterable<Role> getAllRoles(Pageable pageable);
	
	Optional<Role> findRoleById(int roleid);
	
	String deleteRole(int roleid);
	
	Department addDepartment(Department department);
	
	Iterable<Department> getAllDepartments(Pageable pageable);
	
	Optional<Department> findDepartmentbyId(int deptid);
	
	String deleteDepartment(int departmenid);
	
}
