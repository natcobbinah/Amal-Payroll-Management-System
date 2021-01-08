package com.nat.ValueObjects;

import java.io.Serializable;

import java.util.List;
import java.util.Objects;

public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	private int roleid;

	private String rolename;

	private List<Userrole> userroles;

	public Role() {
	}

	public Role(String rolename) {
		super();
		this.rolename = rolename;
	}

	/**
	 * @return the roleid
	 */
	public int getRoleid() {
		return roleid;
	}

	/**
	 * @param roleid the roleid to set
	 */
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	/**
	 * @return the rolename
	 */
	public String getRolename() {
		return rolename;
	}

	/**
	 * @param rolename the rolename to set
	 */
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	/**
	 * @return the userroles
	 */
	public List<Userrole> getUserroles() {
		return userroles;
	}

	/**
	 * @param userroles the userroles to set
	 */
	public void setUserroles(List<Userrole> userroles) {
		this.userroles = userroles;
	}

	public Userrole addUserrole(Userrole userrole) {
		getUserroles().add(userrole);
		userrole.setRole(this);

		return userrole;
	}

	public Userrole removeUserrole(Userrole userrole) {
		getUserroles().remove(userrole);
		userrole.setRole(null);

		return userrole;
	}

	@Override
	public String toString() {
		return "Role [roleid=" + roleid + ", rolename=" + rolename + ", userroles=" + userroles + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleid, rolename, userroles);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return roleid == other.roleid && Objects.equals(rolename, other.rolename)
				&& Objects.equals(userroles, other.userroles);
	}

}