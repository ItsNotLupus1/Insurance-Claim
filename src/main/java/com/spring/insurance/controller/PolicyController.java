package com.spring.insurance.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.insurance.entity.Holder;
import com.spring.insurance.entity.HolderClaim;
import com.spring.insurance.entity.Insurance;
import com.spring.insurance.entity.Policy;
import com.spring.insurance.repo.HolderRepository;
import com.spring.insurance.repo.InsuranceRepository;
import com.spring.insurance.service.PolicyService;

@Controller
public class PolicyController {
	
	@Autowired
	private PolicyService service;
	
	@Autowired
	private InsuranceRepository repo;

	@Autowired
	private HolderRepository holderRepo;

	@ModelAttribute("entity")
	public Holder getDetails() {
		String fname = SecurityContextHolder.getContext().getAuthentication().getName();
		Holder user = holderRepo.findByUsername(fname);
		return user;
	}
	
	@GetMapping("/policyForm")
	public String getPolicy(Model model, @ModelAttribute("entity") Holder user, HolderClaim claim) {
		model.addAttribute("policy", service.getAllIns());
		model.addAttribute("policyAction", new Policy());
		if(user.getPolicy()!= null) {
			Policy policy = user.getPolicy();
			Long id = policy.getIns_id();
			Insurance ins = repo.findById(id).get();
			model.addAttribute("insurance", ins);
			String pattern = "dd MMM yyyy";
			SimpleDateFormat simpleformat = new SimpleDateFormat(pattern);
			String date = simpleformat.format(policy.getStartDate());
			String date1 = simpleformat.format(policy.getEndDate());
			model.addAttribute("date", date);
			model.addAttribute("date1", date1);
		}
		return "policy";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/savePolicy")
	public String addPolicy(@ModelAttribute("policyAction") Policy policy, @RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate, @ModelAttribute("entity") Holder holder) {
		
		Calendar c = Calendar.getInstance();
        c.setTime(fromDate);
        c.add(Calendar.YEAR, 1);
        Date currentDatePlusOne = c.getTime();
        policy.setEndDate(currentDatePlusOne);
        policy.setHolder(holder);
        service.addPolicy(policy);
		return "redirect:/policyForm";
		
	}

}
