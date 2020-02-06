package com.stacksimplify.restservices.mac2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.stacksimplify.restservices.mac2020.entities.User;

//Repository - assign the User Class and the primary key data type
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);
	
}
