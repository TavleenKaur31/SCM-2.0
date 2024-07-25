package com.c.Repositires;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c.Entities.User;

public interface UserRepo extends JpaRepository<User, String> {
	
	Optional<User> findByEmail(String email);

	
}
