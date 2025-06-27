package com.eazybites.loans.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.eazybites.loans.constants.LoansConstants;
import com.eazybites.loans.exception.error.LoanAlreadyExistsException;
import com.eazybites.loans.exception.error.ResourceNotFoundException;
import com.eazybites.loans.mapper.LoansMapper;
import com.eazybites.loans.model.dto.request.LoansUpdateRequestDto;
import com.eazybites.loans.model.dto.response.LoansResponseDto;
import com.eazybites.loans.model.entity.Loans;
import com.eazybites.loans.repository.LoansRepository;
import com.eazybites.loans.service.ILoansService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private final LoansRepository loansRepository;
    private final LoansMapper loansMapper;
    @Override
    public void createLoan(String mobileNumber) {
       Optional<Loans> existingLoan = loansRepository.findByMobileNumber(mobileNumber);
       if(existingLoan.isPresent()) {
           throw new LoanAlreadyExistsException(mobileNumber);
       }
       Loans newLoan = createNewLoan(mobileNumber);
       loansRepository.save(newLoan);
        
    }

    @Override
    public void deleteLoan(String mobileNumber) {
        Loans loan = loansRepository.findByMobileNumber(mobileNumber)
        .orElseThrow(() -> new ResourceNotFoundException("Loans","mobileNumber", mobileNumber));
        loansRepository.delete(loan);
        
    }

    @Override
    public LoansResponseDto fetchLoan(String mobileNumber) {
        Loans loan = loansRepository.findByMobileNumber(mobileNumber)
        .orElseThrow(() -> new ResourceNotFoundException("Loans","mobileNumber", mobileNumber));
        return loansMapper.mapToLoansResponseDto(loan);
    }

    @Override
    public void updateLoan(LoansUpdateRequestDto dto) {
        Loans loan = loansRepository.findByMobileNumber(dto.getMobileNumber()).orElseThrow(() -> new ResourceNotFoundException("Loans","mobileNumber", dto.getMobileNumber()));
        loansMapper.updateLoansFromDto(dto, loan);
        loansRepository.save(loan);
    }

      private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }


}
