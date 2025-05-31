package com.maybank.loan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maybank.loan.dto.LoanApplicationDTO;
import com.maybank.loan.service.LoanApplicationService;

@RestController
@RequestMapping(value = "/api/loans")
public class LoanApplicationController {
	
	private static final Logger log = LoggerFactory.getLogger(LoanApplicationController.class);
	
	private final LoanApplicationService loanService;

	public LoanApplicationController(
		LoanApplicationService loanApplicationService) {
		this.loanService = loanApplicationService;
	}
	
	
	@PostMapping
	public ResponseEntity<LoanApplicationDTO> createLoan(@RequestBody LoanApplicationDTO dto) {
		log.debug("enter createLoan.");
		return ResponseEntity.ok(loanService.create(dto));
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<LoanApplicationDTO> updateLoan(@PathVariable Long id, @RequestBody LoanApplicationDTO dto) {
		log.debug("enter updateLoan.");
		return ResponseEntity.ok(loanService.update(id, dto));
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<LoanApplicationDTO> getLoan(@PathVariable Long id) {
		log.debug("enter getLoan.");
        return ResponseEntity.ok(loanService.getById(id));
    }
	
	
	@GetMapping
    public ResponseEntity<Page<LoanApplicationDTO>> getLoans(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
		log.debug("enter getLoans.");
        return ResponseEntity.ok(loanService.getAll(page, size));
    }
	

}
