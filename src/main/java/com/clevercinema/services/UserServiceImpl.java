package com.clevercinema.services;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clevercinema.dao.UserDao;
import com.clevercinema.dto.RegisterDto;
import com.clevercinema.dto.UserDto;
import com.clevercinema.entity.Authorities;
import com.clevercinema.entity.Users;
import com.clevercinema.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserDao userDao;
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

	@Override
	public List<UserDto> findAllUsersAndHesTickets() {
		
		return userDao.findAllUsersAndHesTickets();
	}

	@Override
	public boolean deleteUserById(int id) {
		
		Users user = userRepository.findById(id).get();
		
		if(user!=null) {
			Set<Authorities> authorities = user.getAuthorities();
			for(Authorities authority : authorities) {
				if(authority.getAuthority().equals("ROLE_ADMIN") || authority.getAuthority().equals("ROLE_ROOT")) {
					return false;
				}
			}
			userDao.deleteUserById(id);
			return true;
		}
		
		return false;
	}

	@Override
	public void deleteRoleById(int id) {
		
		userDao.deleteRoleById(id);
		
	}

	@Override
	public void addRoleByUserId(String authority, int id) {
		userDao.addRoleByUserId(authority,id);
		
	}
	
	

}
