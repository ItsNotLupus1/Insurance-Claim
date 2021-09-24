package com.spring.insurance.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.insurance.entity.Comments;
import com.spring.insurance.entity.Holder;
import com.spring.insurance.entity.Posts;
import com.spring.insurance.entity.Reply;
import com.spring.insurance.repo.CommentRepository;
import com.spring.insurance.repo.HolderRepository;
import com.spring.insurance.repo.PostRepository;
import com.spring.insurance.repo.ReplyRepository;

@Controller
public class ForumController {
	
	@Autowired
	private PostRepository repo;
	
	@Autowired
	private HolderRepository hrepo;
	
	@Autowired
	private CommentRepository crepo;
	
	@Autowired
	private ReplyRepository rrepo;
	
	private Long id;
	
	@ModelAttribute("entity")
	public Holder getDetails() {
		String fname = SecurityContextHolder.getContext().getAuthentication().getName();
		Holder user = hrepo.findByUsername(fname);
		return user;
	}
	
	@GetMapping("listForum")
	public String getAllForums(Model model, @ModelAttribute("entity") Holder user) {
		model.addAttribute("forum", new Posts());
		model.addAttribute("entity", user);
		model.addAttribute("forums", repo.findAll());
		model.addAttribute("count", repo.findAll().size());
		return "forumList";
	}
	
	@GetMapping("forum/{id}")
	public String getForumById(@PathVariable Long id, Model model, Holder users, Comments comment, Reply reply) {
		
		Posts posts = repo.getOne(id);
		Long ids = posts.getHolder_id();
		Holder user = hrepo.findById(ids).get();
		model.addAttribute("user", user);
		model.addAttribute("posted", posts);
		model.addAttribute("com", comment);
		//model.addAttribute("reply", reply);
		this.id = id;
		return "forum";
	}
	
	@RequestMapping(value= "comment", method= RequestMethod.POST)
	public String submitComment(@ModelAttribute("com") Comments comment, @ModelAttribute("entity") Holder holder) {
		comment.setHolder(holder);
		comment.setPost_id(id);
		crepo.save(comment);
		return "redirect:/forum/" + id;
	}
	
	@GetMapping("postForm")
	public String getForums(Model model) {
		model.addAttribute("forum", new Posts());
		return "forumForm";
	}
	
	@GetMapping("updatePost/{id}")
	public String getPost(@PathVariable Long id, Model model) {
		Posts posts = repo.getOne(id);
		model.addAttribute("forum", posts);
		return "forumForm";
	}
	
	@GetMapping("cancel")
	public String cancelForums() {
		return "redirect:/listForum";
	}
	
	@PostMapping("postReply")
	public String submitReply(@ModelAttribute("reply") Reply reply, @ModelAttribute("entity") Holder holder) {
		reply.setHolder(holder);
		rrepo.save(reply);
		return "redirect:/listForum";
	}
	
	@PostMapping("addPost")
	public String postForums(@ModelAttribute("forum") Posts posts) {
		Posts post = posts;
		if(posts.getPost_id() != null) {
			Optional<Posts> posts1 = repo.findById(posts.getPost_id());
			if(posts1.isPresent()) {
				post = posts1.get();
				post.setTitle(posts.getTitle());
				post.setDesc(posts.getDesc());
				post.setHolder_id(post.getHolder_id());
				post.setComments(post.getComments());
			}
		}
		repo.save(post);
		return "redirect:/listForum";
	}
}
