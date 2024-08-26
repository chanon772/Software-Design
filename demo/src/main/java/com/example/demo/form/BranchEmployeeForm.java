package com.example.demo.form;



public class BranchEmployeeForm {
	
	private int branchId;
	private int employeeId;
	
	
	public  BranchEmployeeForm() {
			
		}

	
	
	@Override
	public String toString() {
		return "BranchEmployeeForm [branchId=" + branchId + ", employeeId=" + employeeId + "]";
	}



	public BranchEmployeeForm(int branchId, int employeeId) {
		super();
		this.branchId = branchId;
		this.employeeId = employeeId;
	}



	public int getBranchId() {
		return branchId;
	}
	
	
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	

}
