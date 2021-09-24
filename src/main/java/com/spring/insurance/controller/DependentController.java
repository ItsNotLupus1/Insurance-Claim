package com.spring.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AutoPopulatingList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.insurance.entity.DependentCommand;
import com.spring.insurance.entity.Dependents;
import com.spring.insurance.entity.Holder;
import com.spring.insurance.repo.HolderRepository;
import com.spring.insurance.service.DependentService;

@Controller
public class DependentController {
	
	@Autowired
	private HolderRepository repo;
	
	@Autowired
	private DependentService service;
	
	@ModelAttribute("dependantCommand")
	public DependentCommand getDependantCommand()
	{
		DependentCommand dependantCommand = new DependentCommand();
		dependantCommand.setDepends(new AutoPopulatingList<Dependents>(Dependents.class));
		return dependantCommand;
	}
	
	@ModelAttribute("entity")
	public Holder getDetails() {
		String fname = SecurityContextHolder.getContext().getAuthentication().getName();
		Holder user = repo.findByUsername(fname);
		return user;
	}
	
	@GetMapping("/dependents")
	public String showNameForm()
	{	
		return "dependents";
	}
	
	@GetMapping("/appendDependantsView")
	public String appendDependantField(@RequestParam Integer fieldId, Model model, @ModelAttribute("entity") Holder user)
	{
		model.addAttribute("dependantNumber", fieldId);
		return "formDependantInsert";
	}
	
	@PostMapping("/processClassForm")
	protected String onSubmit(@ModelAttribute("dependantCommand") DependentCommand dependantCommand, Model model)
	{
//		if(result.hasErrors()) {
//			return "dependents";
//		}
		service.dependantsList(dependantCommand);
		model.addAttribute("savedClass", dependantCommand);
		return "redirect:/profile";
	}

}
