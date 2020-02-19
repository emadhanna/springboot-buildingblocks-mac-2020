package com.stacksimplify.restservices.mac2020.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.mac2020.entities.Order;
import com.stacksimplify.restservices.mac2020.entities.User;
import com.stacksimplify.restservices.mac2020.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.mac2020.repositories.OrderRepository;
import com.stacksimplify.restservices.mac2020.repositories.UserRepository;

@RestController
@RequestMapping(value="/hateoas/orders")
@Validated
public class OrderHateoasController {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private UserRepository userRepository;
	
	// get All Orders for a user
	@GetMapping("/{userid}/orders")
	public Resources<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {

		Optional<User> userOptional = userRepository.findById(userid);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");

//		return userOptional.get().getOrders();
		List<Order> allOrders = userOptional.get().getOrders();
		Resources<Order> finalResources = new Resources<Order>(allOrders);
		return finalResources;
	}
	
}
