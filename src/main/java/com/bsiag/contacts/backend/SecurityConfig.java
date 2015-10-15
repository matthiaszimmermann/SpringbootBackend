package com.bsiag.contacts.backend;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalAuthentication
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	final static Logger logger = Logger.getLogger(SecurityConfig.class);
	
    @Value("${basicauth.users}")
    private String propertyUsers;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("configuring users for authentication '" + propertyUsers + "'");
		
		for(String user: Arrays.asList(propertyUsers.split(","))) {
			String [] userElement = user.split(":"); 
			
			try {
				auth.inMemoryAuthentication()
				.withUser(userElement[0])
				.password(userElement[1])
				.roles(userElement[2]);
			} catch (Exception e) {
				logger.error("Failed to parse user element '" + user + "'", e);
			}
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}
}