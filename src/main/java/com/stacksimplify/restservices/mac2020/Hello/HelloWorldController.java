package com.stacksimplify.restservices.mac2020.Hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
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
	
	@GetMapping("/hello-int")
	public String getMessagesinI18NFormat(@RequestHeader(name="Accept-Language"
				, required=false) String locale) {
		return messageSource.getMessage("label.hello", null, new Locale(locale));
	}
	
	@GetMapping("/hello-int2")
	public String getMessagesinI18NFormat2() {
		return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
	}
}
