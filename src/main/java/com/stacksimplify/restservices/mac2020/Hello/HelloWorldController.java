package com.stacksimplify.restservices.mac2020.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	//Simple Method
	//URI - /helloworld
	//GET
	//@RequestMapping(method=RequestMethod.GET, path="/helloworld")
	@GetMapping("/helloworldnew")
	public String helloWorld() {
		
		return "Hello World";
	
	}
	
	@GetMapping("/helloworldbean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Mac", "Hanna", "Newark");
	}
}
