package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Branch;
import com.example.demo.repository.BranchRepository;



@Controller
public class MainController {
	@Autowired
	private BranchRepository branchRepository;
	
	@RequestMapping("/")
	public String index(Model model) {
		
		List<Branch> branches = (List<Branch>) branchRepository.findAll();
		model.addAttribute("branches", branches);
		return "/branch/all";
	}
}
