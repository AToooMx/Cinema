package com.clevercinema.dao;

import java.util.List;

import com.clevercinema.dto.UserDto;

public interface UserDao {

	List<UserDto> findAllUsersAndHesTickets();
	
	void deleteUserById(int id);

	void deleteRoleById(int id);

	void addRoleByUserId(String authority, int id);
}
