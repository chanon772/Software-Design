package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="branch")
public class Branch {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(columnDefinition = "MEDIUMINT NOT NULL AUTO_INCREMENT")
	private int id;	
	private String name;
	private String phonenumber;
	private String email;
	
	public Branch() {};
	public Branch(String name, String phonenumber, String email) {
		super();
		this.name = name;
		this.phonenumber = phonenumber;
		this.email = email;
	}
	
	

	@Override
	public String toString() {
		return "Branch [id=" + id + ", name=" + name + ", phonenumber=" + phonenumber + ", email=" + email + "]";
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

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
}
