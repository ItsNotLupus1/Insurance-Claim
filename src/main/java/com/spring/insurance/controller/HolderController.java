package com.spring.insurance.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.insurance.entity.Holder;
import com.spring.insurance.repo.HolderRepository;
import com.spring.insurance.service.HolderService;

@Controller
public class HolderController {
	
	@GetMapping("/")
	public String index() {
		if(isAuthenticated()) {
			return "redirect:/home";
		}
		return "index";
	}
	
	 @RequestMapping(value = "/homeImage", method = RequestMethod.GET,
	            produces = MediaType.IMAGE_JPEG_VALUE)

	    public void getImage(HttpServletResponse response) throws IOException {

	        ClassPathResource imgFile = new ClassPathResource("resources/static/images/43951.jpg");

	        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
	    }
	
//	@Autowired
//	private RoleRepository roleRepo;
	
	@Autowired
	private HolderService service;
	
	@Autowired
	private HolderRepository repo;
	
	public boolean isAuthenticated() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null || AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass())) {
			return false;
		}
		return auth.isAuthenticated();
	}
	
	@GetMapping("/register")
	public String Register(Model model) {
		if(isAuthenticated()) {
			return "redirect:/home";
		}
		model.addAttribute("entity", new Holder());
		return "holder";
	}
	
	@PostMapping("/result")
	public String postRegister(@Valid @ModelAttribute("entity") Holder holder, BindingResult br, Model model, 
			RedirectAttributes attr) {
		if(br.hasErrors()) {
			return "holder";
		}
		
		else if(repo.existsByEmail(holder.getEmail())) {
			model.addAttribute("error_e", "This email exists, write another email");
			return "holder";
		}
		
		else if(repo.existsByUsername(holder.getUsername())) {
			model.addAttribute("error_u", "This username exists, write another username");
			return "holder";
		}
		service.addHolder(holder);
		service.autoLogin(holder.getUsername(), holder.getPassword());
//		attr.addFlashAttribute("string", "Registration was Successful!, Please login here");
		return "redirect:/home";
	}
	
	@GetMapping("/login")
    public String onLogin(@RequestParam(value = "error", required = false) String error, 
            @RequestParam(value = "logout", required = false) String logout, Model model) {
		if(isAuthenticated()) {
			return "redirect:/home";
		}
		String errorMessge = null;
		if(error != null) {
            errorMessge = "Username or Password is incorrect !!";
        }
        if(logout != null) {
            errorMessge = "You have been successfully logged out !!";
        }
		model.addAttribute("error_string", errorMessge);
        return "login";
    }
	
//	@RequestMapping(value="/login_error")
//    public String onLoginError(Model model) {
//        String error_msg = "Incorrect user or password. Please re-enter.";
//        model.addAttribute("error_string", error_msg);
//        return "login";
//    }
	
	@GetMapping("/home")
	public String postLogin(@Valid Model model) {
		String fname = SecurityContextHolder.getContext().getAuthentication().getName();
		Holder holder = repo.findByUsername(fname);
		model.addAttribute("entity", holder);
		return "home";
	}
	
	@RequestMapping(value = "/profile")
	public String getProfile(Model model) {
		String fname = SecurityContextHolder.getContext().getAuthentication().getName();
		Holder user = repo.findByUsername(fname);
		model.addAttribute("entity", user);
		return "profile";
	}
	
//	@RequestMapping(value="/edit")
//	public String editProfile(Model model) {
//		String fname = SecurityContextHolder.getContext().getAuthentication().getName();
//		Holder user = repo.findByUsername(fname);
//		model.addAttribute("entity", user);
//		return "editProfile";
//	}
	
	@PostMapping("/updateProfile")
	public String updatePro(@ModelAttribute("entity") Holder holder) {
		service.updateHolder(holder);
		return "redirect:/profile";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response, RedirectAttributes model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
//		model.addFlashAttribute("error_string", "You are logged out!, Please login again");
		return "redirect:/login?logout=true";
	}

}
