package com.stacksimplify.restservices.mac2020;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
//Adding Comment for the Spring Boot Application 
@SpringBootApplication
public class SpringbootBuildingblocksMac2020Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBuildingblocksMac2020Application.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	@Bean
	public ResourceBundleMessageSource messageSource() {
		
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
}
