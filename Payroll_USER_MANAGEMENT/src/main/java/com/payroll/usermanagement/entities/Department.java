package com.payroll.usermanagement.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Objects;

/**
 * The persistent class for the department database table.
 * 
 */
@Entity
@NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d")
public class Department extends RepresentationModel<Department> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String departmentid;

	private String departmentname;

	// bi-directional many-to-one association to Userdepartment
	@JsonIgnore
	@OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
	private List<Userdepartment> userdepartments;

	public Department() {
	}

	public Department(String departmentid, String departmentname) {
		super();
		this.departmentid = departmentid;
		this.departmentname = departmentname;
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
	 * @return the departmentid
	 */
	public String getDepartmentid() {
		return departmentid;
	}

	/**
	 * @param departmentid the departmentid to set
	 */
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	/**
	 * @return the departmentname
	 */
	public String getDepartmentname() {
		return departmentname;
	}

	/**
	 * @param departmentname the departmentname to set
	 */
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	/**
	 * @return the userdepartments
	 */
	public List<Userdepartment> getUserdepartments() {
		return userdepartments;
	}

	/**
	 * @param userdepartments the userdepartments to set
	 */
	public void setUserdepartments(List<Userdepartment> userdepartments) {
		this.userdepartments = userdepartments;
	}

	public Userdepartment addUserdepartment(Userdepartment userdepartment) {
		getUserdepartments().add(userdepartment);
		userdepartment.setDepartment(this);

		return userdepartment;
	}

	public Userdepartment removeUserdepartment(Userdepartment userdepartment) {
		getUserdepartments().remove(userdepartment);
		userdepartment.setDepartment(null);

		return userdepartment;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentid=" + departmentid + ", departmentname=" + departmentname
				+ ", userdepartments=" + userdepartments + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(departmentid, departmentname, id, userdepartments);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(departmentid, other.departmentid) && Objects.equals(departmentname, other.departmentname)
				&& id == other.id && Objects.equals(userdepartments, other.userdepartments);
	}

}