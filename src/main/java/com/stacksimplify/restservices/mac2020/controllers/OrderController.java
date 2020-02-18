package com.stacksimplify.restservices.mac2020.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.mac2020.entities.Order;
import com.stacksimplify.restservices.mac2020.entities.User;
import com.stacksimplify.restservices.mac2020.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.mac2020.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.mac2020.repositories.OrderRepository;
import com.stacksimplify.restservices.mac2020.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class OrderController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	// get All Orders for a user
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {

		Optional<User> userOptional = userRepository.findById(userid);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");

		return userOptional.get().getOrders();
	}
	
	@PostMapping("{userid}/orders")
	public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userid);

		if (!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");

		User user = userOptional.get();
		order.setUser(user);
		return orderRepository.save(order);

	}
	
	@GetMapping("{userid}/orders/{orderid}")
	public Order getOrderByOrderId(@PathVariable Long userid, @PathVariable Long orderid) 
			throws OrderNotFoundException, UserNotFoundException {
		Optional<User> user = userRepository.findById(userid);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		Optional<Order> order = orderRepository.findById(orderid);
		if (!order.isPresent()) {
			throw new OrderNotFoundException("Order Not Found");
		}
		
		Order returnedOrder = order.get();
		
		return returnedOrder;		
	}
}