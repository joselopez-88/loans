package com.eazybites.loans.service;

import com.eazybites.loans.model.dto.request.LoansUpdateRequestDto;
import com.eazybites.loans.model.dto.response.LoansResponseDto;

public interface ILoansService {
  void createLoan(String mobileNumber);
  void updateLoan(LoansUpdateRequestDto dto);
  void deleteLoan(String mobileNumber);
  LoansResponseDto fetchLoan(String mobileNumber);
}
