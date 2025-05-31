package com.maybank.loan.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@WebFilter("/*")
public class LoggingFilter implements Filter {
	
	private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// Log request details
		log.info("INCOMING REQUEST: {} {}", req.getMethod(), req.getRequestURI());
		
		// Process the request
		chain.doFilter(request, response);
		
		log.info("OUTGOING RESPONSE: {} {}", res.getStatus(), req.getRequestURI());
		
	}

}
