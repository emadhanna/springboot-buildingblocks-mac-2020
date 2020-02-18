package com.stacksimplify.restservices.mac2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.stacksimplify.restservices.mac2020.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
