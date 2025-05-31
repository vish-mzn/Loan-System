package com.maybank.loan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.annotation.PostConstruct;

@Service
public class CreditScoreService {
	
	private static final Logger log = LoggerFactory.getLogger(CreditScoreService.class);
	private WebClient webClient;
	
	
	@Value("${third.party.url}")
	private String apiUrl;
	
	@PostConstruct
	public void init() {
		this.webClient = WebClient.builder()
				.baseUrl(apiUrl)
				.build();
	}
	
	
	public Integer fetchCreditScore(String idNumber) {
		log.info("Calling external credit score API for ID: {}", idNumber);
		try {
			
			return webClient.get()
					.uri("/score/{id}", idNumber)
					.retrieve()
					.bodyToMono(Integer.class)
					.block();
			
		} catch (Exception e) {
			log.warn("Failed to fetch credit score. Using default score.");
//			throw e;
			return 600; // default value
		}
	}

}
