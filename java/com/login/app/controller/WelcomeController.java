package com.login.app.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.login.app.entity.User;
import com.login.app.serviceImpl.UserService;

@Controller
//@RequestMapping("/myapplication")
public class WelcomeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/welcome")
	public String hello(HttpServletRequest request ) {
		
		request.setAttribute("mode","MODE_HOME");
		return "welcomepage";
	
	}
	@RequestMapping("/register")
	public String registration(HttpServletRequest request) {
		
		request.setAttribute("mode", "MODE_REGISTER");
		return "welcomepage";
	
	}
	
	@PostMapping("/save-user")
	 public String registerUser(@ModelAttribute User user,
			 BindingResult bindingResult,HttpServletRequest request) {
		 userService.saveUser(user);
		 request.setAttribute("mode", "MODE_REGISTER");
		return "welcomepage";
		 
	 }
	
	@GetMapping("/show-users")
	public String showAllUsers(HttpServletRequest request) {
		
		request.setAttribute("users", userService.showAllUsers());
		request.setAttribute("mode", "ALL_USERS");
		return "welcomepage";
	}
	
	@RequestMapping("/delete-user")
	public String deleteUser(@RequestParam Long id, HttpServletRequest request) {
		userService.deleteUser(id);
		request.setAttribute("users", userService.showAllUsers());
		request.setAttribute("mode", "ALL_USERS");
		return "welcomepage";
	}
	
	@RequestMapping("/edit-user")
	public String updateUser(@RequestParam Long id, HttpServletRequest request) {
		
		request.setAttribute("users", userService.updateUser(id));
		request.setAttribute("mode", "MODE_UPDATE");
		return "welcomepage";
	}
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_LOGIN");
		return "welcomepage";
	}
	
	@RequestMapping ("/login-user")
	public String loginUser(@ModelAttribute User user, HttpServletRequest request) {
		if(userService.findByUsernameAndPassword(user.getUsername(), user.getPassword())!=null) {
			return "homepage";
		}
		else {
			request.setAttribute("error", "Invalid Username or Password");
			request.setAttribute("mode", "MODE_LOGIN");
			return "welcomepage";
			
		}
	}
	

}
