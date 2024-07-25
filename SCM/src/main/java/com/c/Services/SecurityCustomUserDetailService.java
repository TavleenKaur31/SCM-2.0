package com.c.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.c.Entities.User;
import com.c.Repositires.UserRepo;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 return repo.findByEmail(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + username));
	}

}
