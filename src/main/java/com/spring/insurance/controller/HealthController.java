package com.spring.insurance.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.insurance.entity.FileUpload;
import com.spring.insurance.entity.Health;
import com.spring.insurance.entity.Holder;
import com.spring.insurance.repo.HolderRepository;
import com.spring.insurance.service.HealthService;

@Controller
public class HealthController {
	
	@Autowired
	private HolderRepository repo;
	
	@Autowired
	HealthService healthService;
	
	@ModelAttribute("FileEntity")
	public Holder getDetails() {
		String fname = SecurityContextHolder.getContext().getAuthentication().getName();
		Holder user = repo.findByUsername(fname);
		return user;
	}
	
	
	@PostMapping("processHealthForm")
	public String processHealthForm(@ModelAttribute("healthAction") @Valid Health health, BindingResult rs, @ModelAttribute("FileEntity")
	Holder user, Model model)
	{
		if(rs.hasErrors()) {
			return "health";
		}
		model.addAttribute("file", new FileUpload());
		healthService.saveHealth(health);
		return "Upload";
	}
}
