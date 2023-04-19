package com.clevercinema.services;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clevercinema.dto.RegisterDto;
import com.clevercinema.entity.Authorities;
import com.clevercinema.entity.Users;
import com.clevercinema.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public boolean save(RegisterDto userDto) {

		if (userRepository.findByEmail(userDto.getEmail()) != null) {
			return false;
		}

		Users user = new Users();
		user.setName(userDto.getName());
		user.setSurname(userDto.getSurname());
		user.setEmail(userDto.getEmail());
		user.setPhone(userDto.getPhone());
		user.setAuthorities(Set.of(new Authorities("ROLE_USER")));
		user.setEnabled(true);
		user.setPassword(encoder.encode(userDto.getPassword()));
		user.setDateChange(new Date());

		userRepository.save(user);

		return true;

	}

}
