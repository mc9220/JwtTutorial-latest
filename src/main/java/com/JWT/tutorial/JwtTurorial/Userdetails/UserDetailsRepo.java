package com.JWT.tutorial.JwtTurorial.Userdetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<UserDetails, Long> {

	List<UserDetails> findByUsername(String username);
}
