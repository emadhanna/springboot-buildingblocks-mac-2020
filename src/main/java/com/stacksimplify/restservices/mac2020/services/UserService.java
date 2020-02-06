package com.stacksimplify.restservices.mac2020.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stacksimplify.restservices.mac2020.entities.User;
import com.stacksimplify.restservices.mac2020.repositories.UserRepository;


@Service
public class UserService {

	//Autowire the User Repository
	@Autowired
	private UserRepository userRepository;

	//Get All Users
	public List<User> getAllUsers(){
		
		return userRepository.findAll();
		
	}
	
	//CreateUser Method
	public User createUser(User user) {
		
		return userRepository.save(user);
	}
	
	//GetUserById Method
	public Optional<User> getUserById(Long id) {
		
		//findById may return optional container, which means it may find he user or may not find it
		Optional<User> user = userRepository.findById(id);
		return user;
		
	}
	
	//UpdateUserById Method
	public User updateUserById(Long id, User user) {
		
		user.setId(id);
		return userRepository.save(user);
	}
	
	//DeleteUserById Method
	public void deleteUserById(Long id) {
		
		if (userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
	}
	
	//FindByUsername Method
	public User getUserByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}
}