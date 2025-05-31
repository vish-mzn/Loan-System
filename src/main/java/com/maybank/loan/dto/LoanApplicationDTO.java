package com.maybank.loan.dto;

import java.math.BigDecimal;

import com.maybank.loan.constant.Status;

public class LoanApplicationDTO {
	
	private Long id;
    private String applicantName;
    private String applicantIdNumber;
    private BigDecimal amount;
    private Integer termInMonths;
    private Status status;
    private Integer creditScore;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public String getApplicantIdNumber() {
		return applicantIdNumber;
	}
	public void setApplicantIdNumber(String applicantIdNumber) {
		this.applicantIdNumber = applicantIdNumber;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getTermInMonths() {
		return termInMonths;
	}
	public void setTermInMonths(Integer termInMonths) {
		this.termInMonths = termInMonths;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Integer getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(Integer creditScore) {
		this.creditScore = creditScore;
	}

}
