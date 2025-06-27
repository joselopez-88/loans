package com.eazybites.loans.exception.error;

public class LoanAlreadyExistsException extends RuntimeException{

    private static final String message = "Loan already registered with given mobileNumber %s";
    public LoanAlreadyExistsException(String mobileNumber) {
        super(String.format(message, mobileNumber));
    }
}
