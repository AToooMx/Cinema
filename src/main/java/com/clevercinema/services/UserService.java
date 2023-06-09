package com.clevercinema.services;

import java.util.List;

import com.clevercinema.dto.RegisterDto;
import com.clevercinema.dto.UserDto;

public interface UserService {

	boolean save(RegisterDto userDto);
	
	List<UserDto> findAllUsersAndHesTickets();
	
	boolean deleteUserById(int id);
	
	void deleteRoleById(int id);

	void addRoleByUserId(String authority, int id);
}
