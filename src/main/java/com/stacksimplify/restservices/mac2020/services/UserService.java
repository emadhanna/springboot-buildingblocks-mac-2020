package com.stacksimplify.restservices.mac2020.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.mac2020.entities.User;
import com.stacksimplify.restservices.mac2020.exceptions.UserExistsException;
import com.stacksimplify.restservices.mac2020.exceptions.UserNotFoundException;
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
	public User createUser(User user) throws UserExistsException{
		//Check if the user exists
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		//If not exists, throw the UserExistsException
		if (existingUser != null) {
			throw new UserExistsException("User Already Exists in Repsitory");
		}
		return userRepository.save(user);
	}
	
	//GetUserById Method
	public Optional<User> getUserById(Long id) throws UserNotFoundException{
		
		//findById may return optional container, which means it may find he user or may not find it
		Optional<User> user = userRepository.findById(id);
		
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found in user repository");
		}
		return user;
	}
	
	//UpdateUserById Method
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		
		Optional<User> optionalUser = userRepository.findById(id);
		
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found in user repository " +
						"provide the correct user id");
		}
		
		user.setUserId(id);
		return userRepository.save(user);
	}
	
	//DeleteUserById Method
	public void deleteUserById(Long id) throws ResponseStatusException{
		Optional<User> optionalUser = userRepository.findById(id);
		
		if (!optionalUser.isPresent()) {
		
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found in user repository " +
					"provide the correct user id");
		}
		
		userRepository.deleteById(id);
	}
	
	//FindByUsername Method
	public User getUserByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}
}