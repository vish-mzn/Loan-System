package com.maybank.loan.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maybank.loan.dto.LoanApplicationDTO;
import com.maybank.loan.entity.LoanApplication;
import com.maybank.loan.exception.LoanNotFoundException;
import com.maybank.loan.repository.LoanApplicationRepository;

@Service
public class LoanApplicationService {
	
	private static final Logger log = LoggerFactory.getLogger(LoanApplicationService.class);
	
	private final LoanApplicationRepository repository;
	private final CreditScoreService creditScoreService;
	
	public LoanApplicationService(
		LoanApplicationRepository repository, 
		CreditScoreService creditScoreService) {
		this.repository = repository;
		this.creditScoreService = creditScoreService;
	}
	
	
	
	/**
     * Create a new loan application after checking uniqueness and fetching credit score.
     */
	
	@Transactional
	public LoanApplicationDTO create(LoanApplicationDTO dto) {
		log.debug("inside create for applicant number: {}", dto.getApplicantIdNumber());
		
		// Prevent duplicate applications by ID number
		if (repository.existsByApplicantIdNumber(dto.getApplicantIdNumber())) {
			throw new IllegalArgumentException("Application already exists");
		}
		
		// External API call
		Integer creditScore = creditScoreService.fetchCreditScore(dto.getApplicantIdNumber());
		
		LoanApplication loan = new LoanApplication();
        loan.setApplicantName(dto.getApplicantName());
        loan.setApplicantIdNumber(dto.getApplicantIdNumber());
        loan.setAmount(dto.getAmount());
        loan.setTermInMonths(dto.getTermInMonths());
        loan.setStatus(dto.getStatus());
        loan.setCreatedAt(LocalDateTime.now());
        loan.setCreditScore(creditScore);
        
        repository.save(loan);
		
		return convertToDTO(loan);
	}
	
	
    @Transactional
    public LoanApplicationDTO update(Long id, LoanApplicationDTO dto) {
        LoanApplication loan = repository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(id));

        loan.setAmount(dto.getAmount());
        loan.setTermInMonths(dto.getTermInMonths());
        loan.setStatus(dto.getStatus());
        loan.setUpdatedAt(LocalDateTime.now());

        return convertToDTO(repository.save(loan));
    }
	
    
    @Transactional(readOnly = true)
    public LoanApplicationDTO getById(Long id) {
        return repository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new LoanNotFoundException(id));
    }
    
    
    @Transactional(readOnly = true)
    public Page<LoanApplicationDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return repository.findAll(pageable).map(this::convertToDTO);
    }
    
	
	
	private LoanApplicationDTO convertToDTO(LoanApplication entity) {
        LoanApplicationDTO dto = new LoanApplicationDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

}
