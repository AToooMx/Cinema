package com.clevercinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clevercinema.entity.Authorities;
import com.clevercinema.entity.Users;

public interface AuthorityReposiroty extends JpaRepository<Authorities, Integer> {

	void deleteByUsers(Users users);
	
}
