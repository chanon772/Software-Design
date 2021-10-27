package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="address")
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(columnDefinition = "MEDIUMINT NOT NULL AUTO_INCREMENT")
	private int	id;
	
	private String	houseNo;
	private String	street;
	private String	alley;
	private String	villageNo;
	private String	subDistrict;
	private String	province;
	private String	postalCode;
	
	@OneToOne(mappedBy = "address")
    private Employee employee;
	
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Address() {};
	public Address(String houseNo, String streeet, String alley, String villageNo, String subDistrict, String province,
			String postalCode) {
		super();
		this.houseNo = houseNo;
		this.streeet = streeet;
		this.alley = alley;
		this.villageNo = villageNo;
		this.subDistrict = subDistrict;
		this.province = province;
		this.postalCode = postalCode;
	}
	
	@OneToOne(mappedBy = "address")
    private Branch branch;
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", houseNo=" + houseNo + ", streeet=" + streeet + ", alley=" + alley
				+ ", villageNo=" + villageNo + ", subDistrict=" + subDistrict + ", province=" + province
				+ ", postalCode=" + postalCode + "]";
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getStreeet() {
		return streeet;
	}
	public void setStreeet(String streeet) {
		this.streeet = streeet;
	}
	public String getAlley() {
		return alley;
	}
	public void setAlley(String alley) {
		this.alley = alley;
	}
	public String getVillageNo() {
		return villageNo;
	}
	public void setVillageNo(String villageNo) {
		this.villageNo = villageNo;
	}
	public String getSubDistrict() {
		return subDistrict;
	}
	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	
	
	
	
}
