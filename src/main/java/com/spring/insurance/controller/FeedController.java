package com.spring.insurance.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.insurance.entity.Feed;
import com.spring.insurance.entity.Holder;
import com.spring.insurance.entity.Item;
import com.spring.insurance.repo.HolderRepository;
import com.spring.insurance.service.FeedService;
import com.sun.syndication.io.FeedException;

@Controller
public class FeedController {
	
	@Autowired
	private FeedService service;
	
    private String message = "";
    
    @Autowired
	private HolderRepository repo;
	
    @ModelAttribute("entity")
	public Holder getclaimDetails() {
		String fname = SecurityContextHolder.getContext().getAuthentication().getName();
		Holder user = repo.findByUsername(fname);
		return user;
	}
    
    @GetMapping("/feedForm")
	public String getFeed(Model model) {
		message = "Please provide new XML RSS Feed information" + service.getError();
        model.addAttribute("message", message);
        model.addAttribute("feed", new Feed());
        return "feedForm";
	}
	
	@GetMapping("/feedList")
    public String viewPersonList(Model model) {
		message = "Please provide new XML RSS Feed information" + service.getError();
        model.addAttribute("message", message);
        model.addAttribute("feed", new Feed());
        model.addAttribute("feeds", service.getAllFeeds());
        return "feedList";
    }
	
	@RequestMapping(value = "/addForm", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("feed") final Feed feed, final BindingResult result, final Model model, RedirectAttributes attr) throws Exception {

        if (result.hasErrors()) {
            message = "Error";
            model.addAttribute("feeds", service.getAllFeeds());
            return "feedList";
        }
        try {
            service.addFeed(feed);
        } catch (FeedException e) {
            message = "URL not walid. Please provide another XML RSS Feed URL";
            model.addAttribute("message", message);
            model.addAttribute("feeds", service.getAllFeeds());
            return "feedList";
        } catch (IOException e) {
            message = "Input output exeption";
            model.addAttribute("message", message);
            model.addAttribute("feeds", service.getAllFeeds());
            return "feedList";
        }
        message = "You added RSS Feed";
        attr.addFlashAttribute("message", message);
        return "redirect:/feedList";
    }
	
	@RequestMapping(value = "/feeds", method = RequestMethod.GET)
    public String createNewFeed(@RequestParam long id, Map<String, Object> feed, Model model) {

        List<Item> mostPopularItem = new ArrayList<>(service.getFeedById(id).getItem());
        // for future can try to catch null exception for sorting
        // if publication date is null in RSS, you get random articles
        if (mostPopularItem.get(1).getPublished() != null) {
            Collections.sort(mostPopularItem, (Item a1, Item a2) -> a1.getPublished().compareTo(a2.getPublished()));
        }
        Collections.reverse(mostPopularItem);
//        if (service.getFeedById(id).getItem().size() > 5) {
//            mostPopularItem = mostPopularItem.subList(0, 5);
//        }
        model.addAttribute("feed", service.getFeedById(id));
        model.addAttribute("item", service.getFeedById(id).getItem());
        model.addAttribute("item_count", service.getFeedById(id).getItem().size());
        model.addAttribute("itemTopFive", mostPopularItem);
        return "feeds";
    }
	
	

}
