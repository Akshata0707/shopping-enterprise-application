package com.userAuth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.userAuth.model.User;
import com.userAuth.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepo;
	
	
	public String registerUser(User user) {
		
		if(userRepo.findByUserName(user.getUserName())!=null) {
			return "Account Already present. Please try logging in";
		}
		
		User newuser=new User();
		newuser.setUserName(user.getUserName());
		newuser.setPassword(user.getPassword());
		newuser.setRole(user.getRole());
		userRepo.save(newuser);
		return "Registered successfully";
	}

	public String loginUser(String userName, String password) {
		
		User user= userRepo.findByUserName(userName).orElseThrow(()->new RuntimeException("invalid username"));
		return password;
	
		
	}

}
