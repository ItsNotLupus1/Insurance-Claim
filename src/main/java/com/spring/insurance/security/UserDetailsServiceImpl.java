package com.spring.insurance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.insurance.entity.Holder;
import com.spring.insurance.entity.MyUserDetails;
import com.spring.insurance.repo.HolderRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
    private HolderRepository userRepository;

    public UserDetailsServiceImpl() {
    }
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Holder user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("user " + username + " is not valid. Please re-enter.");
        }
        
//        org.springframework.security.core.userdetails.User.UserBuilder userBuilder = org.springframework.security.core.userdetails.User.builder();
//        String[] roleNames= user.getRoles().stream().map(Role::getName).toArray(String[]::new);
        return new MyUserDetails(user);
	}

}
