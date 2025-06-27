package com.eazybites.loans.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {

    private String status;
    private int code;
    private String message;
    
}
