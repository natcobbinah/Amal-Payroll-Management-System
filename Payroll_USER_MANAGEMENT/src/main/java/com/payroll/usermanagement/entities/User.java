package com.payroll.usermanagement.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;


import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank
	@NotEmpty
	private String address;

	private String city;

	@javax.validation.constraints.Email
	@NotBlank
	@NotEmpty
	private String email;

	@Column(unique = true)
	private String employeeid;

	private String employeelevel;

	private boolean enabled;

	@NotBlank
	@NotEmpty
	private String name;

	private byte[] password;

	@NotBlank
	@NotEmpty
	private String phonenumber;

	private String bankaccountnumber;

	@Past
	@Temporal(TemporalType.DATE)
	private Date birthdate;

	private String gender;

	@Temporal(TemporalType.DATE)
	private Date hiredate;

	private String maritalstatus;

	private String birthcertid;

	private String driverslicenseid;

	private String passportid;

	private String ssnitid;

	private String votersid;

	// bi-directional many-to-one association to Userdepartment
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Userdepartment> userdepartments = new HashSet<>();

	// bi-directional many-to-one association to Userrole
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Userrole> userroles = new HashSet<>();

	public User() {
	}

	public User(String address, String city, String email, String employeeid, String employeelevel, boolean enabled,
			String name, byte[] password, String phonenumber, String gender, Date birthdate, Date hiredate,
			String maritalstatus, String bankaccountnumber, String birthcertid,String driverslicenseid,String passportid,String ssnitid,String votersid) {
		super();
		this.address = address;
		this.city = city;
		this.email = email;
		this.employeeid = employeeid;
		this.employeelevel = employeelevel;
		this.enabled = enabled;
		this.name = name;
		this.password = password;
		this.phonenumber = phonenumber;
		this.gender = gender;
		this.birthdate = birthdate;
		this.hiredate = hiredate;
		this.maritalstatus = maritalstatus;
		this.bankaccountnumber = bankaccountnumber;
		this.birthcertid = birthcertid;
		this.driverslicenseid = driverslicenseid;
		this.passportid = passportid;
		this.ssnitid = ssnitid;
		this.votersid = votersid;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the employeeid
	 */
	public String getEmployeeid() {
		return employeeid;
	}

	/**
	 * @param employeeid the employeeid to set
	 */
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	/**
	 * @return the employeelevel
	 */
	public String getEmployeelevel() {
		return employeelevel;
	}

	/**
	 * @param employeelevel the employeelevel to set
	 */
	public void setEmployeelevel(String employeelevel) {
		this.employeelevel = employeelevel;
	}

	/**
	 * @return the enabled
	 */
	public boolean getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public byte[] getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(byte[] password) {
		this.password = password;
	}

	/**
	 * @return the phonenumber
	 */
	public String getPhonenumber() {
		return phonenumber;
	}

	/**
	 * @param phonenumber the phonenumber to set
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	/**
	 * @return the bankaccountnumber
	 */
	public String getBankaccountnumber() {
		return bankaccountnumber;
	}

	/**
	 * @param bankaccountnumber the bankaccountnumber to set
	 */
	public void setBankaccountnumber(String bankaccountnumber) {
		this.bankaccountnumber = bankaccountnumber;
	}

	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the hiredate
	 */
	public Date getHiredate() {
		return hiredate;
	}

	/**
	 * @param hiredate the hiredate to set
	 */
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	/**
	 * @return the maritalstatus
	 */
	public String getMaritalstatus() {
		return maritalstatus;
	}

	/**
	 * @param maritalstatus the maritalstatus to set
	 */
	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
	}

	/**
	 * @return the userdepartments
	 */
	public Set<Userdepartment> getUserdepartments() {
		return userdepartments;
	}

	/**
	 * @param userdepartments the userdepartments to set
	 */
	public void setUserdepartments(Set<Userdepartment> userdepartments) {
		this.userdepartments = userdepartments;
	}

	/**
	 * @return the birthcertid
	 */
	public String getBirthcertid() {
		return birthcertid;
	}

	/**
	 * @param birthcertid the birthcertid to set
	 */
	public void setBirthcertid(String birthcertid) {
		this.birthcertid = birthcertid;
	}

	/**
	 * @return the driverslicenseid
	 */
	public String getDriverslicenseid() {
		return driverslicenseid;
	}

	/**
	 * @param driverslicenseid the driverslicenseid to set
	 */
	public void setDriverslicenseid(String driverslicenseid) {
		this.driverslicenseid = driverslicenseid;
	}

	/**
	 * @return the passportid
	 */
	public String getPassportid() {
		return passportid;
	}

	/**
	 * @param passportid the passportid to set
	 */
	public void setPassportid(String passportid) {
		this.passportid = passportid;
	}

	/**
	 * @return the ssnitid
	 */
	public String getSsnitid() {
		return ssnitid;
	}

	/**
	 * @param ssnitid the ssnitid to set
	 */
	public void setSsnitid(String ssnitid) {
		this.ssnitid = ssnitid;
	}

	/**
	 * @return the votersid
	 */
	public String getVotersid() {
		return votersid;
	}

	/**
	 * @param votersid the votersid to set
	 */
	public void setVotersid(String votersid) {
		this.votersid = votersid;
	}

	public Userdepartment addUserdepartment(Userdepartment userdepartment) {
		getUserdepartments().add(userdepartment);
		userdepartment.setUser(this);

		return userdepartment;
	}

	public Userdepartment removeUserdepartment(Userdepartment userdepartment) {
		getUserdepartments().remove(userdepartment);
		userdepartment.setUser(null);

		return userdepartment;
	}

	public Set<Userrole> getUserroles() {
		return this.userroles;
	}

	public void setUserroles(Set<Userrole> userroles) {
		this.userroles = userroles;
	}

	public Userrole addUserrole(Userrole userrole) {
		getUserroles().add(userrole);
		userrole.setUser(this);

		return userrole;
	}

	public Userrole removeUserrole(Userrole userrole) {
		getUserroles().remove(userrole);
		userrole.setUser(null);

		return userrole;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(password);
		result = prime * result + Objects.hash(address, bankaccountnumber, birthcertid, birthdate, city,
				driverslicenseid, email, employeeid, employeelevel, enabled, gender, hiredate, id, maritalstatus, name,
				passportid, phonenumber, ssnitid, userdepartments, userroles, votersid);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(address, other.address) && Objects.equals(bankaccountnumber, other.bankaccountnumber)
				&& Objects.equals(birthcertid, other.birthcertid) && Objects.equals(birthdate, other.birthdate)
				&& Objects.equals(city, other.city) && Objects.equals(driverslicenseid, other.driverslicenseid)
				&& Objects.equals(email, other.email) && Objects.equals(employeeid, other.employeeid)
				&& Objects.equals(employeelevel, other.employeelevel) && enabled == other.enabled
				&& Objects.equals(gender, other.gender) && Objects.equals(hiredate, other.hiredate) && id == other.id
				&& Objects.equals(maritalstatus, other.maritalstatus) && Objects.equals(name, other.name)
				&& Objects.equals(passportid, other.passportid) && Arrays.equals(password, other.password)
				&& Objects.equals(phonenumber, other.phonenumber) && Objects.equals(ssnitid, other.ssnitid)
				&& Objects.equals(userdepartments, other.userdepartments) && Objects.equals(userroles, other.userroles)
				&& Objects.equals(votersid, other.votersid);
	}

}