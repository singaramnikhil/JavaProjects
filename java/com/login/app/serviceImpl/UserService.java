package com.login.app.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.login.app.entity.User;
import com.login.app.jpaRepository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void saveUser(User user) {

		userRepository.save(user);
	}

	public List<User> showAllUsers() 
	{
		
		

		List<User> users = new ArrayList<>();

		for (User user : userRepository.findAll()) {
			users.add(user);
		}

		return users;
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	public Optional<User> updateUser(Long id) {

		return userRepository.findById(id);
		
	}

	public User findByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

}
