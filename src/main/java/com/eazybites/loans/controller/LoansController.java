package com.eazybites.loans.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eazybites.loans.constants.LoansConstants;
import com.eazybites.loans.model.dto.request.LoansUpdateRequestDto;
import com.eazybites.loans.model.dto.response.LoansResponseDto;
import com.eazybites.loans.model.dto.response.ResponseDto;
import com.eazybites.loans.service.ILoansService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path="/api/loans", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@RequiredArgsConstructor
public class LoansController {

    private final ILoansService loansService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@Valid String mobileNumber) {
        loansService.createLoan(mobileNumber);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value(), LoansConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoansResponseDto> fetchLoan(@Valid String mobileNumber) {
        return ResponseEntity.ok(loansService.fetchLoan(mobileNumber));
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseDto> updateLoan(@Valid LoansUpdateRequestDto dto) {
        loansService.updateLoan(dto);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), LoansConstants.MESSAGE_200));

    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoan(@Valid String mobileNumber) {
        loansService.deleteLoan(mobileNumber);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), LoansConstants.MESSAGE_200));
    }
}
