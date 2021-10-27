package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.BranchRepository;
import com.example.demo.model.Branch;



@Controller
public class BranchController {
	
	@Autowired
	private BranchRepository branchRepository;
	
	@GetMapping("/delete/{id}")
	public String deleteBranch(@PathVariable("id") long id, Model model) {
		Branch branch = branchRepository.findById((int) id)
			.orElseThrow(() -> new IllegalArgumentException("Invalid branch Id:" + id));
		
		branchRepository.delete(branch);
		return "redirect:/branch/all";
	}
	
	@GetMapping("/branch/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
	      Branch branch = branchRepository.findById(id)
	    		  .orElseThrow(() -> new IllegalArgumentException("Invalid branch Id:" + id));
	      
	      model.addAttribute("branch", branch);
	      return "branch/edit";
	  }
	
	@PostMapping("/branch/add")
	  public String addBranch(@Validated Branch branch, BindingResult result, Model model) {
	      if (result.hasErrors()) {
	          return "branch/add";
	      }
	      
	      branchRepository.save(branch);
	      return "redirect:/branch/all";
	  }
	
	 @GetMapping("/branch/add")
	  public String showBranchForm(Model model) {
		 Branch branch = new Branch();
	      model.addAttribute("emp", branch);
	      
	      return "branch/add";
	  }
	
}
