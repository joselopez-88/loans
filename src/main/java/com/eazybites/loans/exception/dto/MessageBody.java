package com.eazybites.loans.exception.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MessageBody extends ErrorBody {
    private String message;

}
