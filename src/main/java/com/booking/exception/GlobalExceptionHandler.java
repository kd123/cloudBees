package com.booking.exception;

import com.booking.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Object> InvalidRequestHandler(InvalidRequestException e) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse = apiResponse.buildErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getErrorCode(), e.getErrorMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> DataNotFoundHandler(InvalidRequestException e) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse = apiResponse.buildErrorResponse(HttpStatus.NOT_FOUND.value(), e.getErrorCode(), e.getErrorMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
