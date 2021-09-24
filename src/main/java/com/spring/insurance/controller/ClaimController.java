package com.spring.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.insurance.entity.ClaimModel;
import com.spring.insurance.entity.FileUpload;
import com.spring.insurance.entity.Health;
import com.spring.insurance.entity.Holder;
import com.spring.insurance.entity.HolderClaim;
import com.spring.insurance.repo.HolderRepository;
import com.spring.insurance.service.ClaimService;

@Controller
public class ClaimController {
	
	@Autowired
	private HolderRepository repo;
	
	@Autowired
	private ClaimService service;
	
//	@Autowired
//	private PolicyService polService;
	
	@ModelAttribute("model")
	public ClaimModel getClaimModel()
	{
		ClaimModel model = new ClaimModel();
		model.setClaim(new HolderClaim());
		model.setHealth(new Health());
		model.setFile(new FileUpload());
		return model;
	}
	
	@ModelAttribute("entity")
	public Holder getclaimDetails() {
		String fname = SecurityContextHolder.getContext().getAuthentication().getName();
		Holder user = repo.findByUsername(fname);
		return user;
	}
	
	@GetMapping("/claim")
	public String getForm(Model model, @ModelAttribute("entity") Holder user) {
		return "claimForm";
	}
	
	@PostMapping("/claimedForm")
	public String postForm(@ModelAttribute("model") ClaimModel model, @RequestParam MultipartFile[] fileUpload, 
			@RequestParam("holder_id") Long id) throws Exception {
		
        service.addFullClaim(model, fileUpload, id);
		return "redirect:/home";
		
	}

}
