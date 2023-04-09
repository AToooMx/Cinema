package com.clevercinema.repository;

import org.springframework.data.repository.CrudRepository;

import com.clevercinema.model.Users;

public interface UserRepository extends CrudRepository<Users, Integer> {

	Users findByEmail(String username);
	
	

}
