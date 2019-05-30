package com.login.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.login.app.entity.User;
import com.login.app.serviceImpl.UserService;

@RestController
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value ="/registration")
	public String registration() {
		return "registration";
		
	}
	@GetMapping("/saveUser")
	public String saveUser(@RequestParam String username,@RequestParam String 
			firstname,@RequestParam String lastname,@RequestParam String password,@RequestParam Long age) {
		
		User user = new User(username, firstname, lastname, age, password);
		userService.saveUser(user);
		 
		return "User Saved Succesfully";
		
	}

}
