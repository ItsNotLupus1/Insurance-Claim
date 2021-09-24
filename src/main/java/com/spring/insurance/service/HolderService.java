package com.spring.insurance.service;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.insurance.entity.Holder;
import com.spring.insurance.entity.Role;
import com.spring.insurance.repo.HolderRepository;
import com.spring.insurance.repo.RoleRepository;
import com.spring.insurance.security.UserDetailsServiceImpl;

@Service
@Transactional
public class HolderService {

	@Autowired
	private HolderRepository holderRepo;

	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
    public UserDetailsServiceImpl userDetailsService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	private static final Logger logger = LoggerFactory.getLogger(HolderService.class);

	public void addHolder(Holder holder) {
		
		Holder holder1 = new Holder();
		holder1.setFname(holder.getFname());
		holder1.setLname(holder.getLname());
		holder1.setGender(holder.getGender());
		holder1.setEmail(holder.getEmail());
		holder1.setMobile(holder.getMobile());
		holder1.setUsername(holder.getUsername());
		System.out.println("password " + holder.getPassword());
		String hashedPass = encoder.encode(holder.getPassword());
		holder1.setPassword(hashedPass);
		holder1.setCity(holder.getCity());
		holder1.setState(holder.getState());
		holder1.setDob(holder.getDob());
		Set<Role> role = roleRepo.findByName("USER");
		holder1.setRoles(role);
		holderRepo.save(holder1);
	}
	
	public void autoLogin(String username, String password) {
		
		UserDetails user = userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
		
		authenticationManager.authenticate(token);
		
		if(token.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(token);
			logger.debug(String.format("Auto login %s successfully!", username));
		}
	}

	public Holder getUserById(Long holder_id) {
		return holderRepo.findById(holder_id).get();
	}
	
	public Holder updateHolder(Holder holder) {
		Holder dbUser = holder;
		if(holder.getHolder_id() > 0) {
			Optional<Holder> holder1 = holderRepo.findById(holder.getHolder_id());
			if(holder1.isPresent()) {
				dbUser = holder1.get();
				dbUser.setFname(holder.getFname());
				dbUser.setLname(holder.getLname());
				dbUser.setGender(holder.getGender());
				dbUser.setDob(holder.getDob());
				dbUser.setEmail(dbUser.getEmail());
				dbUser.setUsername(dbUser.getUsername());
				dbUser.setPassword(dbUser.getPassword());
				dbUser.setCity(holder.getCity());
				dbUser.setState(holder.getState());
				dbUser.setMobile(holder.getMobile());
			}
		}
		Holder save = holderRepo.save(dbUser);
		return save;
	}

}
