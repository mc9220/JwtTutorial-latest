package com.JWT.tutorial.JwtTurorial.Userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JWT.tutorial.JwtTurorial.jwt.JwtRequest;
import com.JWT.tutorial.JwtTurorial.jwt.JwtTokenUtil;


@RestController
@RequestMapping("/v1")
public class UserDetailsController {

	@Autowired
	private UserDetailsRepo detailsRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@GetMapping("/welcome")
	public ResponseEntity<?> welcomeUser()
	{
		return new ResponseEntity<>("Welcome to JWt tutorial", HttpStatus.OK);
	}

	@PostMapping("/registerUser")
	public ResponseEntity<UserDetails> registerUser(@RequestBody UserDetails userDetails) {
		UserDetails userDetails2=new UserDetails();
		userDetails2.setUsername(userDetails.getUsername());
		userDetails2.setPassword(passwordEncoder.encode(userDetails.getPassword()));
		return new ResponseEntity<>(detailsRepo.save(userDetails2), HttpStatus.OK);
	}
	@GetMapping("/getUserDetails/{username}")
	public ResponseEntity<?> findUserDetails(@PathVariable("username") String username)
	{
		return new ResponseEntity<>(detailsRepo.findByUsername(username),HttpStatus.OK);
	}
	 @PostMapping("/authenticate")
	    public String authenticateAndGetToken(@RequestBody JwtRequest authRequest) {
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	        if (authentication.isAuthenticated()) {
	            return jwtTokenUtil.generateToken(authRequest.getUsername());
	        } else {
	            throw new UsernameNotFoundException("invalid user request !");
	        }


	    }
}
