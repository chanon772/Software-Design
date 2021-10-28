package com.example.demo.model;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="branch")
public class Branch {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
//	@Column(columnDefinition = "MEDIUMINT NOT NULL AUTO_INCREMENT")
	private int id;	
	private String name;
	private String phoneNumber;
	private String email;
	
	public Branch() {};
	public Branch(String name, String phoneNumber, String email) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "branch_address", 		
	joinColumns  = {@JoinColumn(name="branch_id", referencedColumnName="id")},
	 inverseJoinColumns = 
 	{ @JoinColumn(name = "address_id", referencedColumnName = "id") })
	private Address address;
	
	@OneToMany(mappedBy = "branch",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	private List<Employee> employees;
	
	@Override
	public String toString() {
		return "Branch [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	
}
