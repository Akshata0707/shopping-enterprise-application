package com.userAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userAuth.model.AuthRequest;
import com.userAuth.model.User;
import com.userAuth.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authservice;
	
	@PostMapping("/register")
	public ResponseEntity<String >registerUser(@RequestBody User user){
		authservice.registerUser(user);
		return ResponseEntity.ok("User registered successfully");
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody AuthRequest auth){
		String token=authservice.loginUser(auth.getUserName(), auth.getPassword());
		return ResponseEntity.ok(token);
		
	}
}
