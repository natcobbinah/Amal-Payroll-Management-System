package com.nat.ValueObjects;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Userrole implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private User user;

	private Role role;

	public Userrole() {
	}

	public Userrole(int userid, int roleid) {
		super();
		user = new User();
		role = new Role();
		this.user.setId(userid);
		this.role.setRoleid(roleid);
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

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
}