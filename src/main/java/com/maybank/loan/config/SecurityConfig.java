package com.maybank.loan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				// Public endpoints
				.requestMatchers("/health", "/actuator/**").permitAll()
				
				// PUT requests to /api/loans/** require ADMIN role
				.requestMatchers(HttpMethod.PUT, "/api/loans/**").hasRole("ADMIN")
				
				// All other requests to /api/loans/** just need authentication
				.requestMatchers("/api/loans/**").authenticated()
			.and()
			.httpBasic(); //Basic Auth
		
		return http.build();
	}
	
	
	@Bean
    public UserDetailsService userDetailsService() {
        // ADMIN user
        UserDetails adminUser = User.withUsername("maybank")
                .password("{noop}secret123")
                .roles("ADMIN")
                .build();

        // USER role
        UserDetails normalUser = User.withUsername("maybak")
                .password("{noop}secret321")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(adminUser, normalUser);
    }

}
