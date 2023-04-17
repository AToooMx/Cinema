package com.clevercinema.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clevercinema.entity.Users;
import com.clevercinema.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Users user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Email not found: " + email);
		} else {

			MyUserDetails userDetails = new MyUserDetails(user);
			return userDetails;
		}
	}

}
