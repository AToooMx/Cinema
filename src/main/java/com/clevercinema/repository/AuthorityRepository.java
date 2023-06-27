package com.clevercinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clevercinema.entity.Authorities;
import com.clevercinema.entity.Users;

public interface AuthorityRepository extends JpaRepository<Authorities, Integer> {

	void deleteByUsers(Users users);
	
}
