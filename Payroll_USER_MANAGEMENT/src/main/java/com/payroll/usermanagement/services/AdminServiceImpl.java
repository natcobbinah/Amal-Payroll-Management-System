package com.payroll.usermanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.payroll.usermanagement.entities.Department;
import com.payroll.usermanagement.entities.Role;
import com.payroll.usermanagement.entities.User;
import com.payroll.usermanagement.entities.Userdepartment;
import com.payroll.usermanagement.entities.Userrole;
import com.payroll.usermanagement.exceptionhandling.ResourceNotFoundException;
import com.payroll.usermanagement.repositories.DepartmentRepository;
import com.payroll.usermanagement.repositories.RoleRepository;
import com.payroll.usermanagement.repositories.UserDepartmentRepository;
import com.payroll.usermanagement.repositories.UserRepository;
import com.payroll.usermanagement.repositories.UserroleRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserroleRepository userroleRepository;

	@Autowired
	UserDepartmentRepository userdepartmentRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public User addUser(User user) {
		User addedUser = userRepository.save(user);
		return addedUser;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findUserbyId(int userid) {
		return userRepository.findById(userid);
	}

	@Override
	public ResponseEntity<?> deleteUserbyId(int userid) {
		Optional<User> id = userRepository.findById(userid);
		if (id.isEmpty()) {
			throw new ResourceNotFoundException("User with id " + userid + " not found");
		}
		userRepository.deleteById(userid);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public User findUserByEmployeeid(String employeeid) {
		return userRepository.findUserByemployeeid(employeeid);
	}

	@Override
	public List<User> findUserByName(String name) {
		return userRepository.findUserByName(name);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}

	@Override
	public User disableUser(String employeeid) {
		User user = userRepository.findUserByemployeeid(employeeid);
		if (user.getEnabled() == true) {
			user.setEnabled(false);
			userRepository.save(user);
		}
		return user;
	}

	@Override
	public List<Userrole> getUsersbyRole(int roleid) {
		return userroleRepository.getUsersByRole(roleid);
	}

	@Override
	public List<Userdepartment> getUserbyDepartment(int departmentid) {
		return userdepartmentRepository.getUsersbyDepartment(departmentid);
	}

	@Override
	public Role addRole(Role role) {
		Role addedRole = roleRepository.save(role);
		return addedRole;
	}

	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> findRoleById(int roleid) {
		return roleRepository.findById(roleid);
	}

	@Override
	public String deleteRole(int roleid) {
		roleRepository.deleteById(roleid);
		return "Role deleted successfully";
	}

	@Override
	public Department addDepartment(Department department) {
		Department addedDepartment = departmentRepository.save(department);
		return addedDepartment;
	}

	@Override
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	@Override
	public Optional<Department> findDepartmentbyId(int deptid) {
		return departmentRepository.findById(deptid);
	}

	@Override
	public String deleteDepartment(int departmentid) {
		departmentRepository.deleteById(departmentid);
		return "Department deleted successfully";
	}

	@Override
	public List<User> deleteUsersbyId(List<String> values) {
		for (int i = 0; i < values.size(); i++) {
			userRepository.deleteById(Integer.parseInt(values.get(i)));
		}
		return userRepository.findAll();
	}

	@Override
	public List<User> disableUsers(List<String> employeeids) {
		for (int i = 0; i < employeeids.size(); i++) {
			User user = userRepository.findUserByemployeeid(employeeids.get(i));
			if (user.getEnabled() == true) {
				user.setEnabled(false);
				userRepository.save(user);
			}
		}
		return userRepository.findAll();
	}

}
