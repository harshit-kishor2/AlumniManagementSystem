package com.harshit.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/activateAccount").setViewName("activateAccount");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/updateUserAccount").setViewName("updateUserAccount");
	}
}