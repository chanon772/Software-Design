package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeRepository;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	 
	  @PostMapping("/employee/all/{id}")
	  public String updateEmployee(@PathVariable("id") Integer id, @Validated  Employee employee, 
	    BindingResult result, Model model) {
	     System.out.println(id); 
		 System.out.println(employee.getId()+ employee.getFirstname());
		if (result.hasErrors()) {
	          employee.setId(id);
	          return "/employee/all";
	      }
	          
	      employeeRepository.save(employee);
	      return "redirect:/all";
	  }
	  @GetMapping("/employee/edit/{id}")
	  public String showUpdateForm(@PathVariable("id") int id, Model model) {
	      Employee employee = employeeRepository.findById(id)
	        .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
	      
	      model.addAttribute("employee", employee);
	      return "/employee/edit";
	  }
	  
	  @GetMapping(path="/all")
	  public @ResponseBody Iterable<Employee> getAllEmployees() {
	    return employeeRepository.findAll();
	  }
	  
	  @PostMapping("/employee/add")
	  public String addEmployee(@Validated Employee employee, BindingResult result, Model model) {
	      if (result.hasErrors()) {
	          return "add";
	      }
	      
	      employeeRepository.save(employee);
	      return "redirect:/all";
	  }
	  
	  @GetMapping("/employee/add")
	  public String showForm(Model model) {
	      Employee employee = new Employee();
	      model.addAttribute("employee", employee);
	      
	      return "/employee/add";
	  }
}
