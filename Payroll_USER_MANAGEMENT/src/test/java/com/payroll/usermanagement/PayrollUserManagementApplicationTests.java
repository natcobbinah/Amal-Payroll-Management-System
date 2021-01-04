package com.payroll.usermanagement;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.payroll.usermanagement.entities.Department;
import com.payroll.usermanagement.entities.Role;
import com.payroll.usermanagement.entities.User;
import com.payroll.usermanagement.entities.Userdepartment;
import com.payroll.usermanagement.entities.Userrole;
import com.payroll.usermanagement.repositories.DepartmentRepository;
import com.payroll.usermanagement.repositories.RoleRepository;
import com.payroll.usermanagement.repositories.UserDepartmentRepository;
import com.payroll.usermanagement.repositories.UserRepository;
import com.payroll.usermanagement.repositories.UserroleRepository;

@SpringBootTest
class PayrollUserManagementApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	UserDepartmentRepository userDepartmentRepository;

	@Autowired
	UserroleRepository userroleRepository;
	
	/*
	 * @Autowired Usereligibilityforwork usereligibilityforworkRepository;
	 */

	// -----------start user CRUD operations unit tests------------------------
	@Test
	public void addUser() {// works
		userRepository.save(new User("P.O.BOX MC 3292,Niger", "Accra", "Big@gmail.com", "0001BIG", "Senior Associate", true,
				"Big big", "big".getBytes(), "0554036804","Male",new Date(),new Date(),"Single","9269768967967868","BIGT34","DRV354","PASSPORT3453",
				"SSNIT345345","VRTS2235"));
	}

	@Test
	public void getAllUsers() {// works
		List<User> findAll = userRepository.findAll();
		for (User u : findAll) {
			System.out.println(u);
		}
	}

	@Test
	public void findSingleUser() {// works
		User user = userRepository.findById(6).get();
		System.out.println(user);
	}

	@Test
	public void deleteUser() { // works
		userRepository.deleteById(6);
	}

	@Test
	public void findUserByEmployeeid() {// works
		User findUserByemployeeid = userRepository.findUserByemployeeid("0001BAT");
		System.out.println(findUserByemployeeid);
	}

	@Test
	public void findUserByName() {// works
		List<User> findUserByName = userRepository.findUserByName("Kat kat");
		for(User u: findUserByName) {
			System.out.println(u);
		}
	}

	@Test
	public void disableUser() {// works
		User user = userRepository.findUserByemployeeid("0001BAT");
		if (user.getEnabled() == true) {
			user.setEnabled(false);
			userRepository.save(user);
		} else {
			System.out.println("Users status has been already disabled");
		}
	}
	
	@Test
	public void disableandDeleteUser() {
		/*
		 * User user = userRepository.findUserByemployeeid("0001TOM"); if
		 * (user.getEnabled() == true) { user.setEnabled(false);
		 * userRepository.save(user); }
		 */
		userRepository.disableandDeleteUser("0001TOM");
	}

	@Test
	public void getUserByEmail() {//works
		User user = userRepository.getUserByEmail("bat@gmail.com");
		System.out.println(user);
	}

	// -----------start role CRUD operations unit tests------------------------
	@Test
	public void addRole() {// Works
		//roleRepository.save(new Role("HR"));
		//roleRepository.save(new Role("ADMIN"));
		//roleRepository.save(new Role("ACCOUNTANT"));
		//roleRepository.save(new Role("EMPLOYEE"));
	}

	@Test
	public void getAllRoles() {// works
		List<Role> findAll = roleRepository.findAll();
		for (Role r : findAll) {
			System.out.println(r);
		}
	}

	@Test
	public void findRoleById() {// works
		Optional<Role> findById = roleRepository.findById(2);
		System.out.println(findById);
	}

	@Test
	public void deleteRole() { // works
		roleRepository.deleteById(2);
	}

	@Test
	public void updateRole() {
		Role role = roleRepository.findById(4).get();
		if (role != null) {
			role.setRolename("HRE");
			roleRepository.save(role);
		} else {
			System.out.println("Role does not exist");
		}
	}

	// -----------start DEPARTMENT CRUD operations unit  tests--------
	@Test
	public void addDepartment() {// works
		 departmentRepository.save(new Department("SDE","Software Development"));
		 departmentRepository.save(new Department("DS","Data Science"));
		//departmentRepository.save(new Department("SLF", "SalesForce"));
	}

	@Test
	public void getAllDepartments() {// works
		List<Department> findAll = departmentRepository.findAll();
		for (Department d : findAll) {
			System.out.println(d);
		}
	}

	@Test
	public void getSingleDepartment() {// works
		Optional<Department> findById = departmentRepository.findById(2);
		System.out.println(findById);
	}

	@Test
	public void deleteSingleDepartment() {// works
		departmentRepository.deleteById(3);
	}

	// -----------start Userrole CRUD operations unit tests------------------------
	@Test
	public void addUserRole() {// works
		userroleRepository.save(new Userrole(1, 1));
		userroleRepository.save(new Userrole(2, 3));
		userroleRepository.save(new Userrole(2, 4));
	}

	@Test
	public void getAllUserRoles() {// works
		List<Userrole> findAll = userroleRepository.findAll();
		for (Userrole u : findAll) {
			System.out.println(u);
		}
	}

	@Test
	public void getUsersByUserrole() {// works
		List<Userrole> role = userroleRepository.getUsersByRole(1);
		for (Userrole usr : role) {
			System.out.println(usr);
		}
	}

	// -----------start UserDepartment CRUD operations unit
	// tests------------------------
	@Test
	public void addUserDepartment() {// works
		//userDepartmentRepository.save(new Userdepartment(1, 1));
		userDepartmentRepository.save(new Userdepartment(2, 2));
	}

	@Test
	public void getAllUserDepartments() {// works
		List<Userdepartment> all = userDepartmentRepository.findAll();
		for (Userdepartment ud : all) {
			System.out.println(ud);
		}
	}

	@Test
	public void getUsersByDepartment() {// works
		List<Userdepartment> department = userDepartmentRepository.getUsersbyDepartment(2);
		for (Userdepartment ud : department) {
			System.out.println(ud);
		}
	}
	
	@Test
	public void verifyLoginRecords() {// works
		Optional<User>	userRecord= userRepository.getUserByEmailandPassword("kat@gmail.com", "kat".getBytes());
		if(userRecord.isPresent()) {
			System.out.println(userRecord.get().getEmail() + " : " + userRecord.get().getPassword() + " : " + 
					userRecord.get().getUserroles() + userRecord.get().getEnabled());
		}else {
			System.out.println("No record of user found in the system: Login failed");
		}
	}
	
}






