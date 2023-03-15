package com.JWT.tutorial.JwtTurorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.JWT.tutorial.JwtTurorial.Userdetails.UserDetailsRepo;
import com.JWT.tutorial.JwtTurorial.jwt.UserInfoToUserDetails;

@SpringBootApplication
public class JwtTurorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtTurorialApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Autowired
			private UserDetailsRepo repo;

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				// TODO Auto-generated method stub
				com.JWT.tutorial.JwtTurorial.Userdetails.UserDetails userDetails = repo.findByUsername(username).get(0);
				return new UserInfoToUserDetails(userDetails);
			}
		};
	}
}
