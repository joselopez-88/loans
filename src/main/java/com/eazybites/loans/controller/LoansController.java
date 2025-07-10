package com.eazybites.loans.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybites.loans.constants.LoansConstants;
import com.eazybites.loans.model.dto.LoansContactInfoDto;
import com.eazybites.loans.model.dto.request.LoansUpdateRequestDto;
import com.eazybites.loans.model.dto.response.LoansResponseDto;
import com.eazybites.loans.model.dto.response.ResponseDto;
import com.eazybites.loans.model.entity.Loans;
import com.eazybites.loans.service.ILoansService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path="/api/loans", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@RequiredArgsConstructor
public class LoansController {

    private final ILoansService loansService;

    @Value("${build.version}")
    private String buildVersion;

    private final Environment environment;

    private final LoansContactInfoDto contactInfo;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam @Pattern(regexp = "(^[0-9]{10}$)", message = " The mobile number must be 10 digits.") String mobileNumber) {
        loansService.createLoan(mobileNumber);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value(), LoansConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoansResponseDto> fetchLoan(@RequestParam @Pattern(regexp = "(^[0-9]{10}$)", message = " The mobile number must be 10 digits.") String mobileNumber) {
        return ResponseEntity.ok(loansService.fetchLoan(mobileNumber));
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseDto> updateLoan(@Valid @RequestBody LoansUpdateRequestDto dto) {
        loansService.updateLoan(dto);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), LoansConstants.MESSAGE_200));

    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoan(@RequestParam @Pattern(regexp = "(^[0-9]{10}$)", message = " The mobile number must be 10 digits.") String mobileNumber) {
        loansService.deleteLoan(mobileNumber);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), LoansConstants.MESSAGE_200));
    }

    @Operation(
        summary = "Get Build Version Rest Api",
        description = "Get Build Version details in EazyBank"
    )
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildVersion() {
        return ResponseEntity.ok().body(buildVersion);
    }
    @GetMapping("/java-home")
    public ResponseEntity<String> getJavaHome(){
        return ResponseEntity.ok().body(environment.getProperty("JAVA_HOME"));
    }
    @GetMapping("/contact-info")
    public ResponseEntity<LoansContactInfoDto> getContactInfo() {
        return ResponseEntity.ok().body(contactInfo);
    }

}
