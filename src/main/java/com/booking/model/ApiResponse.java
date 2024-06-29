package com.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse<T> {

    private boolean success;
    private int code;
    private String errorCode;
    private String message;
    private T data;

    public ApiResponse<T> buildErrorResponse(int code,String errorCode,String message){
        this.setCode(code);
        this.setErrorCode(errorCode);
        this.setMessage(message);
        return this;
    }


    public ApiResponse<T> buildSuccessResponse(int code,String message,T data){
        this.setSuccess(true);
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
        return this;
    }
}
