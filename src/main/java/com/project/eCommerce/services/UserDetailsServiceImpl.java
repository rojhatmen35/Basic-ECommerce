package com.project.eCommerce.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.eCommerce.entities.User;
import com.project.eCommerce.repositories.UserRepositories;
import com.project.eCommerce.security.JwtUserDetails;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepositories userRepository;

	public UserDetailsServiceImpl(UserRepositories userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		return JwtUserDetails.create(user);
	}

	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id).get();
		return JwtUserDetails.create(user);
	}

}