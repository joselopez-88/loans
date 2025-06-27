package com.eazybites.loans.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class LoansUpdateRequestDto {

    @NotBlank(message = " The mobile number is required.")
    @Pattern(regexp = "(^[0-9]{10}$)", message = " The mobile number must be 10 digits.")
    private String mobileNumber;
    @NotBlank(message = " The loan number is required.")
    @Pattern(regexp = "(^[0-9]{12}$)", message = " The loan number must be 12 digits.")
    private String loanNumber;
    @NotBlank(message = " The loan type is required.")
    private String loanType;
    @Positive(message = " The total loan amount must be greater than 0.")
    private int totalLoan;
    @PositiveOrZero(message = " The amount paid must be greater than or equal to 0.")
    private int amountPaid;
    @PositiveOrZero(message = " The outstanding amount must be greater than or equal to 0.")
    private int outstandingAmount;
}
