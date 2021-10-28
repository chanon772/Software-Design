package com.example.demo.controller;

import com.example.demo.repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	@PostMapping("/branch/add_employee/{branch_id}/{employee_id}")
	public String addEmployeeToBranch(@PathVariable("branch_id") Integer branch_id, @PathVariable("employee_id") Integer employee_id,  BindingResult result, Model model) {
		
	    
		//Check if  not found;
		Branch branch = branchRepository.findById(branch_id).get();
	    Employee employee = employeeRepository.findById(employee_id).get();

	    employee.setBranch(branch);
		employeeRepository.save(employee);

		return "redirect:/branch/detail/branch_id";
	}
	
	@PostMapping("/branch/add_employee")
	public String testaddEmployeeToBranchForm(@Validated BranchEmployeeForm form, BindingResult result, Model model) {
		
		
		
	    int branch_id = form.getBranchId();
	    int employee_id = form.getEmployeeId();
	    
	    
	    
	    Branch branch = branchRepository.findById(branch_id).get();
	    Employee employee = employeeRepository.findById(employee_id).get();
	    
	    System.out.println(form);
	    System.out.println(branch_id);
	    System.out.println(employee_id);
	    System.out.println(branch);
	    System.out.println(employee);
	    
	    employee.setBranch(branch);
		employeeRepository.save(employee);
		
//		branch.getEmployees().add(employee);
//		branchRepository.save(branch);
		
		return branchDetail(branch_id, model);
	}
	
	@GetMapping("/branch/add_employee")
	public String testaddEmployeeToBranchForm(Model model) {
		
	   
	    model.addAttribute("form",  new BranchEmployeeForm());

		return "/test/add_employee";
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
