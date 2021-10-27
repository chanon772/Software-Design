package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeRepository;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@RequestMapping("/employee/all")
	public String employeeForm(Model model) {
		   
		  List<Employee> employees = (List<Employee>) employeeRepository.findAll();
		  model.addAttribute("employees",employees);
	    return "employee/all";
	  }
	
	@GetMapping("/employee/delete/{id}")
	  public String deleteEmployee(@PathVariable("id") long id, Model model) {
	      Employee employee = employeeRepository.findById((int) id)
	        .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
	      employeeRepository.delete(employee);
	      return "redirect:/employee/all";
	  }
	 
	  @PostMapping("/employee/update/{id}")
	  public String updateEmployee(@PathVariable("id") Integer id, @Validated  Employee employee, 
	    BindingResult result, Model model) {
	     System.out.println(employee); 
		 //System.out.println(employee.getId()+ employee.getFirstname());
		if (result.hasErrors()) {
	          employee.setId(id);
	          return "employee/edit";
	      }
	          
	      employeeRepository.save(employee);
	      return "redirect:/employee/all";
	  }
	  @GetMapping("/employee/edit/{id}")
	  public String showUpdateForm(@PathVariable("id") int id, Model model) {
	      Employee employee = employeeRepository.findById(id)
	        .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
	      
	      model.addAttribute("employee", employee);
	      return "employee/edit";
	  }
	  
	  @GetMapping(path="/all")
	  public @ResponseBody Iterable<Employee> getAllEmployees() {
	    return employeeRepository.findAll();
	  }
	  
	  @PostMapping("/employee/add")
	  public String addEmployee(@Validated Employee employee, BindingResult result, Model model) {
	      if (result.hasErrors()) {
	          return "employee/add";
	      }
	      
	      employeeRepository.save(employee);
	      return "redirect:/employee/all";
	  }
	  
	  @GetMapping("/employee/add")
	  public String showForm(Model model) {
	      Employee employee = new Employee();
	      model.addAttribute("employee", employee);
	      
	      return "employee/add";
	  }
}
