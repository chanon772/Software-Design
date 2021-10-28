package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(columnDefinition = "MEDIUMINT NOT NULL AUTO_INCREMENT")
	 private Integer id;
	 private String ssn;
	 private String firstName;
	 private String middleName;
	 private String lastName;
	 private String sex;
	 private String birthDate;
	 private String phoneNumber;
	 private Integer role;
	 private String status;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 @JoinTable(name = "employee_address",
	 joinColumns  = {@JoinColumn(name="employee_id", referencedColumnName="id")},
	 inverseJoinColumns = 
	     {@JoinColumn(name = "address_id", referencedColumnName = "id") })
	 private Address address;
	 public Address getAddress() {
		return address;
	 }
	 public void setAddress(Address address) {
		this.address = address;
	 }
	 
			
	@Override
	public String toString() {
		return "Employee [id=" + id + ", ssn=" + ssn + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", sex=" + sex + ", birthDate=" + birthDate + ", phoneNumber="
				+ phoneNumber + ", role=" + role + ", status=" + status + "]";
	}

	public Employee() {
		super();
	}

	public Employee(Integer id, String ssn, String firstName, String middleName, String lastName, String sex,
			String birthDate, String phoneNumber, Integer role, String status) {
		super();
		this.id = id;
		this.ssn = ssn;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.sex = sex;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.status = status;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

	
	 
}
