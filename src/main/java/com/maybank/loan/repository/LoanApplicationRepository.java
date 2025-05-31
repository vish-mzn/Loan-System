package com.maybank.loan.repository;

import com.maybank.loan.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
	
	boolean existsByApplicantIdNumber(String applicantIdNumber);

}
