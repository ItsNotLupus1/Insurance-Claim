package com.spring.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.insurance.entity.Holder;
import com.spring.insurance.entity.Hospital;
import com.spring.insurance.entity.Insurance;
import com.spring.insurance.entity.Policy;
import com.spring.insurance.repo.HolderRepository;
import com.spring.insurance.repo.HospitalRepository;
import com.spring.insurance.repo.InsuranceRepository;
import com.spring.insurance.service.SearchService;

@Controller
public class HospitalController {
	
	@Autowired
	private HospitalRepository repo;
	
	@Autowired
	private HolderRepository holderRepo;
	
	@Autowired
	InsuranceRepository insRepo;
	
	@Autowired
	private SearchService service;

	@ModelAttribute("entity")
	public Holder getDetails() {
		String fname = SecurityContextHolder.getContext().getAuthentication().getName();
		Holder user = holderRepo.findByUsername(fname);
		return user;
	}
	
		
	@RequestMapping(method = RequestMethod.GET, value="/search")
	public String viewHomePage(Model model, @Param("keyword") String keyword) {
		List<Hospital> hospital = service.listAll(keyword);
		model.addAttribute("hospital", hospital);
		model.addAttribute("keyword", keyword);

		return "HospitalList";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/hospForm")
	public String getHospForm(Model model) {
		model.addAttribute("hospital", new Hospital());
		return "hospform";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/saveHosp")
	public String addHosp(Model model, @ModelAttribute("hospital") Hospital hospital) {
		repo.save(hospital);
		return "home";
	}
	
	
	@GetMapping("/getMaps")
	public String getMaps(@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude, Model model) {
		System.out.println("Latitude is "+ latitude + " Longitude is " + longitude);
		Hospital hos = repo.findByLatitude(latitude);
		model.addAttribute("hos", hos);

		return "map";
	}
	
	@GetMapping("/getFullList")
	public String getFullList(Integer page, Model model, @ModelAttribute("entity") Holder holder)
	{	
		Policy policy = holder.getPolicy();
		Long id = policy.getIns_id();
		Insurance insurance  = insRepo.findById(id).get();
		List<Hospital> hosp = insurance.getHospital();
		model.addAttribute("hospital", hosp);
		return "HospitalList";
	}
	
	@GetMapping("/uploadFilterForm")
	public String getSearchWithMap(Model model, @ModelAttribute("entity") Holder holder,
								   @RequestParam("city") String city, 
								   @RequestParam("ratings") Integer ratings, 
								   @RequestParam("state") String state,
								   @RequestParam("ratingString") String ratingString)
	{
		System.out.println("City is "+ city);
		System.out.println("ratings is" + ratings);
		System.out.println("state is" + state);
		System.out.println("ratingString is" + ratingString);
		
		Policy policy = holder.getPolicy();
		Long id = policy.getIns_id();
		Insurance insurance  = insRepo.findById(id).get();
		List<Hospital> hosp = insurance.getHospital();
		
		if(city!=null && ratings==0 && state.equalsIgnoreCase("defaultState") && ratingString.equalsIgnoreCase("defaultRatingString"))
		{
			List<Hospital> hospi = repo.findByInsuranceAndCityOrderByRatingsDesc(insurance, city);
			model.addAttribute("hospital", hospi);
			
		}
			
	else if(city.equalsIgnoreCase("defaultCity") && ratings.equals(5) && state.equalsIgnoreCase("defaultState") && ratingString.equalsIgnoreCase("defaultRatingString"))
		{
			hosp = repo.findByRatingsEquals(ratings); 
			model.addAttribute("hospital", hosp);
		}
		
		
		else if(city.equalsIgnoreCase("defaultCity") && ratings.equals(4) && state.equalsIgnoreCase("defaultState") && ratingString.equalsIgnoreCase("defaultRatingString"))
		{
			hosp = repo.findByRatingsEquals(ratings); 
			model.addAttribute("hospital", hosp);
		}
		
		else if(city.equalsIgnoreCase("defaultCity") && ratings.equals(3) && state.equalsIgnoreCase("defaultState") && ratingString.equalsIgnoreCase("defaultRatingString"))
		{
			hosp = repo.findByRatingsEquals(ratings); 
			model.addAttribute("hospital", hosp);
		}
		
		else if(city.equalsIgnoreCase("defaultCity") && ratings.equals(2) && state.equalsIgnoreCase("defaultState") && ratingString.equalsIgnoreCase("defaultRatingString"))
		{
			hosp = repo.findByRatingsEquals(ratings); 
			model.addAttribute("hospital", hosp);
		}
		
		else if(city.equalsIgnoreCase("defaultCity") && ratings.equals(1) && state.equalsIgnoreCase("defaultState") && ratingString.equalsIgnoreCase("defaultRatingString"))
	{
		hosp = repo.findByRatingsEquals(ratings); 
			model.addAttribute("hospital", hosp);
	}
		
		else if(city.equalsIgnoreCase("defaultCity") && ratings == 0 && state != null && ratingString.equalsIgnoreCase("defaultRatingString"))
		{
			hosp = repo.findByState(state);
			model.addAttribute("hospital", hosp);
		}
		
		else if(city.equalsIgnoreCase("defaultCity") && ratings == 0 & ratingString.equalsIgnoreCase("HighToLow") && state.equalsIgnoreCase("defaultState"))
		{
			hosp = repo.findByOrderByRatingsDesc();
			model.addAttribute("hospital", hosp);
		}
		
		else if(city.equalsIgnoreCase("defaultCity") && ratings==0 & ratingString.equalsIgnoreCase("LowToHigh") && state.equalsIgnoreCase("defaultState"))
		{
			hosp = repo.findByOrderByRatingsAsc();
			model.addAttribute("hospital", hosp);
		}
		
		else if(city!=null && ratings!=0 && state.equalsIgnoreCase("defaultState") && ratingString.equalsIgnoreCase("defaultRatingString"))
		{
			System.out.println("Chicken is the best - yuk");
			hosp = repo.findByCityAndRatings(city,ratings); 
			model.addAttribute("hospital", hosp);
		}
		
		else if(city!= null && ratings!=0 & ratingString.equalsIgnoreCase("DefaultRatingString") && state != null)
		{
			hosp = repo.findByCityAndAndStateAndRatingsOrderByRatingsDesc(city, state, ratings);
			model.addAttribute("hospital", hosp);
		}
		
		else if(city.equalsIgnoreCase("defaultCity") && ratings!=null && state !=null && ratingString.equalsIgnoreCase("defaultRatingString"))
		{
			hosp = repo.findByStateAndRatingsOrderByRatingsDesc(state, 5); 
			model.addAttribute("hospital", hosp);
		}
		
		else {
			System.out.println("Hello world");
		}
		
		
		return "HospitalList";
	}
	
}
