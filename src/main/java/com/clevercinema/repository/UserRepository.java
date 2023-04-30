package com.clevercinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.clevercinema.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	Users findByEmail(@Param("email") String email);

}
