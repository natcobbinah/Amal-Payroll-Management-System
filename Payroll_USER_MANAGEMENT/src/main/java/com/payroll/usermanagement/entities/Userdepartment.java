package com.payroll.usermanagement.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the userdepartment database table.
 * 
 */
@Entity
@NamedQuery(name = "Userdepartment.findAll", query = "SELECT u FROM Userdepartment u")
public class Userdepartment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	// bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name = "deptid")
	private Department department;

	public Userdepartment() {
	}

	public Userdepartment(int userid, int deptid) {
		super();
		user = new User();
		department = new Department();
		this.user.setId(userid);
		this.department.setId(deptid);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}	
}