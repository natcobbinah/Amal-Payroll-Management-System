package com.payroll.usermanagement;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@RestController
@Api(value = "Admin and User_Login operations", description = "admin and UserLogin API")
@RequestMapping({ "/v1" })
@CrossOrigin(maxAge = 3600)
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
	@PreAuthorize("hasAnyAuthority('ADMIN','HR')")
	@PostMapping("/admin/user")
	@ApiOperation(value = "Creates a  new User", notes = "The newly created User with Id will be sent in the location "
			+ "response header ", response = User.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User created successfully", response = User.class),
			@ApiResponse(code = 500, message = "Error creating user", response = ErrorDetail.class) })
	public User addUser(@Valid @RequestBody User user) {
		User addUser = adminService.addUser(user);
		// set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newUserUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
				.toUri();
		responseHeaders.setLocation(newUserUri);
		
		return addUser;
	}

	@PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
	@PatchMapping("/admin/user")
	@ApiOperation(value = "Updates User Record", notes = "Updates the existing user record", response = User.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User updated successfully", response = User.class),
			@ApiResponse(code = 500, message = "Error updating user", response = ErrorDetail.class) })
	public User updateUser(@Valid @RequestBody User user) {
		User addUser = adminService.addUser(user);
		return addUser;
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/user")
	@ApiOperation(value = "Retrieves all Users", notes = "Retrieves all users of the system", response = User.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "All user records retrieved successfully", response = User.class),
			@ApiResponse(code = 500, message = "Error Retrieving user records", response = ErrorDetail.class) })
	public Iterable<User> getAllUsers(Pageable pageable) {
		Iterable<User> allUsers = adminService.getAllUsers(pageable);
		for (User user : allUsers) {
			updateUserResourceWithLinks(user, pageable);
		}
		return allUsers;
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@ApiOperation(value = "Deletes an existing User", notes = "Deletes user from the system", response = User.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User deleted successfully", response = User.class),
			@ApiResponse(code = 500, message = "Error deleting user", response = ErrorDetail.class) })
	@DeleteMapping("/admin/user")
	public void deleteUser(@RequestBody User user) {
		adminService.deleteUser(user);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/deleteuser/{userid}")
	@ApiOperation(value = "Deletes User Record by userID", notes = "Deletes a user record", response = User.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User deleted successfully", response = User.class),
			@ApiResponse(code = 500, message = "Error deleting user", response = ErrorDetail.class) })
	public ResponseEntity<?> deleteUserbyId(@PathVariable("userid") int userid) {
		return adminService.deleteUserbyId(userid);
	}
	

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/deleteusers")
	@ApiOperation(value = "Deletes multiple Users Record by userID", notes = "Deletes  users records", response = User.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Users deleted successfully", response = User.class),
			@ApiResponse(code = 500, message = "Error deleting users", response = ErrorDetail.class) })
	public Iterable<User> deleteMultipleUsers(@RequestParam List<String> values) {
		return adminService.deleteUsersbyId(values);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/disableuser/{employeeid}")
	@ApiOperation(value = "Disables User Record by employeeID", notes = "Disables  user record", response = User.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User disabled successfully", response = User.class),
			@ApiResponse(code = 500, message = "Error disabling user", response = ErrorDetail.class) })
	public User disableUser(@PathVariable("employeeid") String employeeid) {
		return adminService.disableUser(employeeid);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/disableusers")
	@ApiOperation(value = "Disables multiple Users Records by employeeID", notes = "Disables  users records", response = User.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Users disabled successfully", response = User.class),
			@ApiResponse(code = 500, message = "Error disabling users", response = ErrorDetail.class) })
	Iterable<User> disableUsers(@RequestParam List<String> employeeid) {
		return adminService.disableUsers(employeeid);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/deleterole/{roleid}")
	@ApiOperation(value = "Deletes Role by roleID", notes = "Deletes a role from the system", response = Role.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Role deleted successfully", response = Role.class),
			@ApiResponse(code = 500, message = "Error deleting role", response = ErrorDetail.class) })
	public String deleteRole(@PathVariable("roleid") int roleid) {
		return adminService.deleteRole(roleid);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/deletedepartment/{departmentid}")
	@ApiOperation(value = "Deletes department by departmentID", notes = "Deletes a department from the system", response = Department.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "department deleted successfully", response = Department.class),
			@ApiResponse(code = 500, message = "Error deleting department", response = ErrorDetail.class) })
	public String deleteDepartment(@PathVariable("departmentid") int departmentid) {
		return adminService.deleteDepartment(departmentid);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/user/{userid}")
	@ApiOperation(value = "Finds a single User by userID", notes = "Finds a user by a given Id", response = User.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User found with  success", response = User.class),
			@ApiResponse(code = 500, message = "Could not find user", response = ErrorDetail.class) })
	public User findUserById(@PathVariable("userid") int userid) {
		User user = adminService.findUserbyId(userid);
		return user;
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/empid/{employeeid}")
	@ApiOperation(value = "Finds a single User by employeeId", notes = "Finds a user by a given employeeId", response = User.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User with given employeeId  found with  success", response = User.class),
			@ApiResponse(code = 500, message = "Could not find user", response = ErrorDetail.class) })
	public User findUserByEmployeeid(@PathVariable("employeeid") String employeeid) {
		return adminService.findUserByEmployeeid(employeeid);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/username/{name}")
	@ApiOperation(value = "Finds a single User by name", notes = "Finds a user by a username", response = User.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User with given username  found with  success", response = User.class),
			@ApiResponse(code = 500, message = "Could not find user", response = ErrorDetail.class) })
	public List<User> findUserByName(@PathVariable("name") String name) {
		return adminService.findUserByName(name);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/useremail/{email}")
	@ApiOperation(value = "Finds a single User by email", notes = "Finds a user by given email address", response = User.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User with given email address   found with  success", response = User.class),
			@ApiResponse(code = 500, message = "Could not find user", response = ErrorDetail.class) })
	public User findUserByEmail(@PathVariable("email") String email) {
		return adminService.findUserByEmail(email);
	}

	@PreAuthorize("hasAuthority('EMPLOYEE')")
	@GetMapping("/admin/userroles/{userid}")
	@ApiOperation(value = "Finds user with Userroles by userID", notes = "Finds a user with corresponding user roles", response = Userrole.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User  found with  success", response = Userrole.class),
			@ApiResponse(code = 500, message = "Could not find user", response = ErrorDetail.class) })
	public List<Userrole> getUsersbyRole(@PathVariable("userid") int userid) {
		return adminService.getUsersbyRole(userid);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/userdepartment/{userid}")
	@ApiOperation(value = "Finds user with Userdepartments by userID", notes = "Finds a user with corresponding user departments", response = Userdepartment.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User  found with  success", response = Userdepartment.class),
			@ApiResponse(code = 500, message = "Could not find user", response = ErrorDetail.class) })
	public List<Userdepartment> getUserbyDepartment(@PathVariable("userid") int userid) {
		return adminService.getUserbyDepartment(userid);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/admin/role")
	@ApiOperation(value = "Creates a  new Role", notes = "The newly created Role with Id will be sent in the location "
			+ "response header ", response = Role.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Role created successfully", response = Role.class),
			@ApiResponse(code = 500, message = "Error creating Role", response = ErrorDetail.class) })
	public Role addRole(@RequestBody Role role) {
		Role addedRole = adminService.addRole(role);
		return addedRole;
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/role")
	@ApiOperation(value = "Retrieves all Roles", notes = "Retrieves all roles of the system", response = Role.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "All role records retrieved successfully", response = Role.class),
			@ApiResponse(code = 500, message = "Error Retrieving role records", response = ErrorDetail.class) })
	public Iterable<Role> getAllRoles(Pageable pageable) {
		return adminService.getAllRoles(pageable);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/role/{roleid}")
	@ApiOperation(value = "Finds a single Role by roleID", notes = "Finds a role by its ID", response = Role.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Role with id  found with  success", response = Role.class),
			@ApiResponse(code = 500, message = "Could not find role", response = ErrorDetail.class) })
	public Optional<Role> findRoleById(@PathVariable("roleid") int roleid) {
		return adminService.findRoleById(roleid);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/admin/departments")
	@ApiOperation(value = "Creates a  new Department", notes = "The newly created Department with Id will be sent in the location "
			+ "response header ", response = Department.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Department created successfully", response = Department.class),
			@ApiResponse(code = 500, message = "Error creating Department", response = ErrorDetail.class) })
	public Department addDepartment(@RequestBody Department department) {
		Department addedDepartment = adminService.addDepartment(department);
		return addedDepartment;
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/departments")
	@ApiOperation(value = "Retrieves all Departments", notes = "Retrieves all departments of the system", response = Department.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "All department records retrieved successfully", response = Department.class),
			@ApiResponse(code = 500, message = "Error Retrieving department records", response = ErrorDetail.class) })
	public Iterable<Department> getAllDepartments(Pageable pageable) {
		return adminService.getAllDepartments(pageable);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/departments/{deptid}")
	@ApiOperation(value = "Finds a single Department by departmentID", notes = "Finds a department by its ID", response = Department.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Department with id  found with  success", response = Department.class),
			@ApiResponse(code = 500, message = "Could not find department", response = ErrorDetail.class) })
	public Optional<Department> findDepartmentbyId(@PathVariable("deptid") int deptid) {
		return adminService.findDepartmentbyId(deptid);
	}

	//method to add links using HAL for (rest HATEOAS) implementation
	private void updateUserResourceWithLinks(User user, Pageable pageable) {
		//you can add .slash(path) to extend the above resourcelinks if the path involves (/)paths
		user.add(linkTo(methodOn(PayrollUserManagementApplication.class).getAllUsers(pageable)).slash(user.getId())
				.withSelfRel());

		user.add(linkTo(methodOn(PayrollUserManagementApplication.class).getUsersbyRole(user.getId()))
				.withRel("Userroles"));

		user.add(linkTo(methodOn(PayrollUserManagementApplication.class).getUserbyDepartment(user.getId()))
				.withRel("Userdepartments"));
	}
}
