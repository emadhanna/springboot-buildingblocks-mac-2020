package com.stacksimplify.restservices.mac2020.controllers;

import java.util.List;
import java.util.Optional;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.stacksimplify.restservices.mac2020.entities.Order;
import com.stacksimplify.restservices.mac2020.entities.User;
import com.stacksimplify.restservices.mac2020.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.mac2020.repositories.UserRepository;
import com.stacksimplify.restservices.mac2020.services.UserService;

@RestController
@RequestMapping(value="/hateoas/users")
@Validated
public class UserHateoasController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	//implement the get all users method
	@GetMapping
	public Resources<User> getAllUsers() throws UserNotFoundException{
		
//		return userService.getAllUsers();
		List<User> allUsers = userService.getAllUsers();
		for (User user: allUsers) {
			//Self Link
			Long userId = user.getUserId();
			Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			user.add(selfLink);
			
			//Relationship link with getAllOrders
			Resources<Order> orders = ControllerLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userId);
			Link orderLink = ControllerLinkBuilder.linkTo(orders).withRel("All-Orders");
			user.add(orderLink);
		}
		//Self Link for the URI getAllUsers
		Link selfLinkgetAllUsers = ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel();
		Resources<User> finalResources = new Resources<User>(allUsers, selfLinkgetAllUsers);
		return finalResources;
	}
	
	//Create getUserById Method
	@GetMapping("/{id}")
	public Resource<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		
		try {
//			return userService.getUserById(id);
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			Long userId = user.getUserId();
			Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			user.add(selfLink);
			Resource<User> finalResource = new Resource<User>(user);
			return finalResource;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
