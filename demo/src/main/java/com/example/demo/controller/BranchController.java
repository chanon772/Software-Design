package com.example.demo.controller;

import com.example.demo.repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.BranchRepository;
import com.example.demo.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import com.example.demo.form.BranchEmployeeForm;
import com.example.demo.model.Address;
import com.example.demo.model.Branch;
import com.example.demo.model.Employee;



@Controller
public class BranchController {
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private RelationshipRepository relationshipRepository =  new RelationshipRepository();
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
    private  JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/branch/all")
	public String allBranches(Model model) {
		List<Branch> branches = (List<Branch>) branchRepository.findAll();
		model.addAttribute("branches", branches);
		return "/branch/all";
	}
	
	@GetMapping("/branch/delete/{id}")
	public String deleteBranch(@PathVariable("id") long id, Model model) {
		Branch branch = branchRepository.findById((int) id)
			.orElseThrow(() -> new IllegalArgumentException("Invalid branch Id:" + id));
		
		branchRepository.delete(branch);
		return "redirect:/branch/all";
	}
	
	
	@GetMapping("/branch/add_employee/{branch_id}/{employee_id}")
	public String addEmployeeToBranch(@PathVariable("branch_id") Integer branch_id, @PathVariable("employee_id") Integer employee_id, Model model) {
		
	    
		//Check if  not found;
		Branch branch = branchRepository.findById(branch_id).get();
	    Employee employee = employeeRepository.findById(employee_id).get();

	    employee.setBranch(branch);
		employeeRepository.save(employee);

		return  branchDetail(branch_id, model);
	}
	
	
	@GetMapping("/branch/remove_employee/{branch_id}/{employee_id}")
	public String removeEmployeeFromBranch(@PathVariable("branch_id") Integer branch_id, @PathVariable("employee_id") Integer employee_id, Model model) {
		
		
		String sql = "DELETE FROM branch_employee WHERE branch_id = ? AND employee_id = ?";
	    Object[] args = new Object[] {branch_id, employee_id, };
	    
	    jdbcTemplate.update(sql, args);
	    
//		model.addAttribute("employees",  employees);

		return branchDetail(branch_id, model);
	}
	
	@GetMapping("/branch/{branch_id}/add_employee")
	public String addEmployeeToBranchWithOnlyAvailableEmployee(@PathVariable("branch_id") Integer branch_id, Model model) {
		System.out.println(branch_id);
	   List<Employee> employees  =  relationshipRepository.findAllAvailableEmployee();
	   
	   Branch branch = branchRepository.findById(branch_id)
	    		  .orElseThrow(() -> new IllegalArgumentException("Invalid branch Id:" + branch_id));
	   
	   model.addAttribute("branch", branch);
	   model.addAttribute("employees",  employees);

		return "/branch/add_employee";
	}
	
	
	
	
	@GetMapping("/branch/detail/{branch_id}")
	public String branchDetail(@PathVariable("branch_id") int branch_id, Model model) {
	      Branch branch = branchRepository.findById(branch_id)
	    		  .orElseThrow(() -> new IllegalArgumentException("Invalid branch Id:" + branch_id));
	      
	      
	    
	      model.addAttribute("employees",  branch.getEmployees());
		  model.addAttribute("address", branch.getAddress());
	      model.addAttribute("branch", branch);
	      
	      return "branch/detail";
	  }
	
	
	@GetMapping("/branch/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
	      Branch branch = branchRepository.findById(id)
	    		  .orElseThrow(() -> new IllegalArgumentException("Invalid branch Id:" + id));
	      
	      
		  model.addAttribute("address", branch.getAddress());
	      model.addAttribute("branch", branch);
	      
	      return "branch/edit";
	  }
	
	@PostMapping("/branch/update/{id}")
	  public String updateUser(@PathVariable("id") Integer id, @Validated  Branch branch,
	    BindingResult result, Model model) {
	     
		if (result.hasErrors()) {
			branch.setId(id);
	          return "/branch/edit";
	      }
		
	    Integer address_id = relationshipRepository.findAddressIdByBranchId(branch.getId());
	    Address address = addressRepository.findById(address_id).get();
	    
	    
	    branch.getAddress().setId(address.getId());
	    
		branchRepository.save(branch);
		
		return "redirect:/branch/all";
	  }
	
	@PostMapping("/branch/add")
	  public String addBranch(@Validated Branch branch, @Validated Address address, BindingResult result, Model model) {
		
	      if (result.hasErrors()) {
	          return "branch/add";
	      }
	      
	      branch.setAddress(address);
//	      addressRepository.save(address);
	     
	      branchRepository.save(branch);
	      
	      
	      return "redirect:/branch/all";
	  }
	
	 @GetMapping("/branch/add")
	  public String showBranchForm(Model model) {
		 Branch branch 	  = new Branch();
		 Address address  = new Address();
		 
		 
		 model.addAttribute("address",address);
	     model.addAttribute("branch", branch);
	      
	      return "branch/add";
	  }
	
}
