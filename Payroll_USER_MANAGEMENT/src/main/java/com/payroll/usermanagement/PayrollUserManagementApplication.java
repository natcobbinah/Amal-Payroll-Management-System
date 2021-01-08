package com.payroll.usermanagement;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payroll.usermanagement.entities.Department;
import com.payroll.usermanagement.entities.Role;
import com.payroll.usermanagement.entities.User;
import com.payroll.usermanagement.entities.Userdepartment;
import com.payroll.usermanagement.entities.Userrole;
import com.payroll.usermanagement.exceptionhandling.ErrorDetail;
import com.payroll.usermanagement.services.AdminService;
import com.payroll.usermanagement.services.UserCredentialsandRolesService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@EnableDiscoveryClient
@EnableCircuitBreaker
@RestController
@Api(value = "Admin and User_Login operations", description = "admin and UserLogin API")
@RequestMapping({"/v1"})
@CrossOrigin(maxAge = 3600)
@SpringBootApplication
public class PayrollUserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayrollUserManagementApplication.class, args);
	}

	@Autowired
	AdminService adminService;

	@Autowired
	UserCredentialsandRolesService userCredentialsandRoleService;

	/*****************************************************************
	 * RESTFUL SERVICES ENDPOINTS FOR USER LOGIN AND ROLES ASSIGNMENT
	 *****************************************************************/
	// All these uri are versioned as (v1)
	@GetMapping("/userLogin/{email}/{password}")
	public Set<Userrole> verifyLoginRecords(@PathVariable("email") String email,
			@PathVariable("password") String password) {
		return userCredentialsandRoleService.verifyLoginRecords(email, password);
	}

	/**************************************************
	 * RESTFUL SERVICES ENDPOINTS FOR ADMIN
	 **************************************************/
	@PostMapping("/admin/user")
	@ApiOperation(value = "Creates a  new User", notes = "The newly created User with Id will be sent in the location "
			+ "response header ", response = User.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User created successfully", response = User.class),
			@ApiResponse(code = 500, message = "Error creating user", response = ErrorDetail.class) })
	public User addUser(@Valid @RequestBody User user) {
		User addUser = adminService.addUser(user);
		return addUser;
	}

	// used to update user records
	@PatchMapping("/admin/user")
	public User updateUser(@Valid @RequestBody User user) {
		User addUser = adminService.addUser(user);
		return addUser;
	}

	@GetMapping("/admin/user")
	@ApiOperation(value = "Retrieves all Users", response = User.class, responseContainer = "List")
	public Iterable<User> getAllUsers(Pageable pageable) {
		return adminService.getAllUsers(pageable);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/deleteuser/{userid}")
	public ResponseEntity<?> deleteUserbyId(@PathVariable("userid") int userid) {
		return adminService.deleteUserbyId(userid);
	}

	@GetMapping("/admin/deleteusers")
	public Iterable<User> deleteMultipleUsers(@RequestParam List<String> values) {
		return adminService.deleteUsersbyId(values);
	}

	@GetMapping("/admin/disableuser/{employeeid}")
	public User disableUser(@PathVariable("employeeid") String employeeid) {
		return adminService.disableUser(employeeid);
	}

	@GetMapping("/admin/disableusers")
	Iterable<User> disableUsers(@RequestParam List<String> employeeid) {
		return adminService.disableUsers(employeeid);
	}

	@GetMapping("/admin/deleterole/{roleid}")
	public String deleteRole(@PathVariable("roleid") int roleid) {
		return adminService.deleteRole(roleid);
	}

	@GetMapping("/admin/deletedepartment/{departmentid}")
	public String deleteDepartment(@PathVariable("departmentid") int departmentid) {
		return adminService.deleteDepartment(departmentid);
	}

	@GetMapping("/admin/userid/{userid}")
	public Optional<User> findUserById(@PathVariable("userid") int userid) {
		return adminService.findUserbyId(userid);
	}

	@GetMapping("/admin/empid/{employeeid}")
	public User findUserByEmployeeid(@PathVariable("employeeid") String employeeid) {
		return adminService.findUserByEmployeeid(employeeid);
	}

	@GetMapping("/admin/username/{name}")
	public List<User> findUserByName(@PathVariable("name") String name) {
		return adminService.findUserByName(name);
	}

	@GetMapping("/admin/useremail/{email}")
	public User findUserByEmail(@PathVariable("email") String email) {
		return adminService.findUserByEmail(email);
	}

	@GetMapping("/admin/userroles/{roleid}")
	public List<Userrole> getUsersbyRole(@PathVariable("roleid") int roleid) {
		return adminService.getUsersbyRole(roleid);
	}

	@GetMapping("/admin/userdepartment/{deptid}")
	public List<Userdepartment> getUserbyDepartment(@PathVariable("deptid") int deptid) {
		return adminService.getUserbyDepartment(deptid);
	}

	@PostMapping("/admin/role")
	public Role addRole(@RequestBody Role role) {
		Role addedRole = adminService.addRole(role);
		return addedRole;
	}

	@GetMapping("/admin/role")
	public Iterable<Role> getAllRoles(Pageable pageable) {
		return adminService.getAllRoles(pageable);
	}

	@GetMapping("/admin/role/{roleid}")
	public Optional<Role> findRoleById(@PathVariable("roleid") int roleid) {
		return adminService.findRoleById(roleid);
	}

	@PostMapping("/admin/departments")
	public Department addDepartment(@RequestBody Department department) {
		Department addedDepartment = adminService.addDepartment(department);
		return addedDepartment;
	}

	@GetMapping("/admin/departments")
	public Iterable<Department> getAllDepartments(Pageable pageable) {
		return adminService.getAllDepartments(pageable);
	}

	@GetMapping("/admin/departments/{deptid}")
	public Optional<Department> findDepartmentbyId(@PathVariable("deptid") int deptid) {
		return adminService.findDepartmentbyId(deptid);
	}

}
