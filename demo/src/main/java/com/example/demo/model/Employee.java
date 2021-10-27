package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {
	
	 private Integer id;
	 private String ssn;
	 private String firstname;
	 private String middlename;
	 private String lastname;
	 private String sex;
	 private String birth_date;
	 private String phonenumber;
	 private Integer role;
	 
	@Override
	public String toString() {
		return "Employee [id=" + id + ", ssn=" + ssn + ", firstname=" + firstname + ", middlename=" + middlename
				+ ", lastname=" + lastname + ", sex=" + sex + ", birth_date=" + birth_date + ", phonenumber="
				+ phonenumber + ", role=" + role + "]";
	}

	public Employee() {
		super();
	}

	public Employee(Integer id, String ssn, String firstname, String middlename, String lastname, String sex,
			String birth_date, String phonenumber, Integer role) {
		
		this.id = id;
		this.ssn = ssn;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.sex = sex;
		this.birth_date = birth_date;
		this.phonenumber = phonenumber;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
	
	
	
	
	
	

}
