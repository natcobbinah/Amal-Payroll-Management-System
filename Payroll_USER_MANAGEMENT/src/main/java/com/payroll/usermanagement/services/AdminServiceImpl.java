package com.payroll.usermanagement.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.payroll.usermanagement.entities.Department;
import com.payroll.usermanagement.entities.Role;
import com.payroll.usermanagement.entities.User;
import com.payroll.usermanagement.entities.Userdepartment;
import com.payroll.usermanagement.entities.Userrole;
import com.payroll.usermanagement.exceptionhandling.ResourceNotFoundException;
import com.payroll.usermanagement.passwordEncoder.PasswordEncoder;
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
		//User addedUser = userRepository.save(user);
		//return addedUser;
		User usertoAdd = new User();
		usertoAdd.setAddress(user.getAddress());
		usertoAdd.setCity(user.getCity());
		usertoAdd.setEmail(user.getEmail());
		usertoAdd.setEmployeeid(user.getEmployeeid());
		usertoAdd.setEmployeelevel(user.getEmployeelevel());
		
		usertoAdd.setEnabled(true); //user is by default set as as enabled 
		usertoAdd.setName(user.getName());
		
		String mypass = PasswordEncoder.encoder().encode(user.getPassword());
		usertoAdd.setPassword(mypass);
		
		usertoAdd.setPhonenumber(user.getPhonenumber());
		usertoAdd.setGender(user.getGender());
		usertoAdd.setBirthdate(user.getBirthdate());
		usertoAdd.setHiredate(user.getHiredate());
		usertoAdd.setMaritalstatus(user.getMaritalstatus());
		usertoAdd.setBankaccountnumber(user.getBankaccountnumber());
		usertoAdd.setBirthcertid(user.getBirthcertid());
		usertoAdd.setDriverslicenseid(user.getDriverslicenseid());
		usertoAdd.setPassportid(user.getPassportid());
		usertoAdd.setSsnitid(user.getSsnitid());
		usertoAdd.setVotersid(user.getVotersid());
		
		return userRepository.save(usertoAdd);
	}

	/*****************************************************
	 * Implementing paging and sorting to data
	 * which may be very large on an API call request
	 *****************************************************/
	@Override
	public Iterable<User> getAllUsers(Pageable pageable) {
		Page<User> allUsers = userRepository.findAll(pageable);
		return allUsers;
	}

	@Override
	public User findUserbyId(int userid) {
		return userRepository.findById(userid).get();
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
	public List<Userrole> getUsersbyRole(int userid) {
		return userroleRepository.getUsersByRole(userid);
	}

	@Override
	public List<Userdepartment> getUserbyDepartment(int userid) {
		return userdepartmentRepository.getUsersbyDepartment(userid);
	}

	@Override
	public Role addRole(Role role) {
		Role addedRole = roleRepository.save(role);
		return addedRole;
	}

	@Override
	public Iterable<Role> getAllRoles(Pageable pageable) {
		 Page<Role> allRoles = roleRepository.findAll(pageable);
		 return allRoles;
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
	public Iterable<Department> getAllDepartments(Pageable pageable) {
		 Page<Department> allDepartments = departmentRepository.findAll(pageable);
		 return allDepartments;
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
	public Iterable<User> deleteUsersbyId(List<String> values) {
		for (int i = 0; i < values.size(); i++) {
			userRepository.deleteById(Integer.parseInt(values.get(i)));
		}
		return userRepository.findAll();
	}

	@Override
	public Iterable<User> disableUsers(List<String> useridvalues) {
		for (int i = 0; i < useridvalues.size(); i++) {
			User user = userRepository.findById(Integer.parseInt(useridvalues.get(i))).get();
			if (user.getEnabled() == true) {
				user.setEnabled(false);
				userRepository.save(user);
			}
		}
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
	
	/*
	 * public Iterable<Role> getAllRoles(Pageable pageable) { Page<Role> allRoles =
	 * roleRepository.findAll(pageable); return allRoles; }
	 */
	@Override
	public Iterable<Userrole> getAllUserRoles(Pageable pageable) {
		 Page<Userrole> alluserroles = userroleRepository.findAll(pageable);
		 return alluserroles;
	}

}
