package com.nat.ValueObjects;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Userdepartment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private User user;
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