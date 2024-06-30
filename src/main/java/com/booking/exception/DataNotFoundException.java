package com.booking.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DataNotFoundException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    public DataNotFoundException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
