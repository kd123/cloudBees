package com.booking.constants;

import lombok.Getter;

@Getter
public enum ErrorConstant {

    BAD_REQUEST_INVALID_INPUT("BK_400_1","The input format provided is invalid"),
    USER_NOT_FOUND("BK_400_2","invalid user id"),
    BAD_REQUEST_INVALID_DATA("BK_400_3","Data cannot be null"),
    BAD_REQUEST_INVALID_FIRST_NAME("BK_400_4","First name cannot be empty"),
    BAD_REQUEST_INVALID_LAST_NAME("BK_400_6","Last name cannot be empty"),
    BAD_REQUEST_INVALID_BOOKING_ID("BK_400_7","BOOKING id is invalid"),
    BAD_REQUEST_EMPTY_PAYMENT_MODE("BK_400_8","Payment mode cannot be empty"),
    BAD_REQUEST_EMPTY_EMAIL("BK_400_9"," Email cannot be empty"),
    BAD_REQUEST_EMPTY_FROM("BK_400_10","From cannot be empty"),
    BAD_REQUEST_EMPTY_TO("BK_400_11"," To cannot be empty"),
    DATA_NOT_FOUND("BK_404_1"," Data not Found"),
    BAD_REQUEST_EMPTY_SECTION_TYPE("BK_400_12"," Section Type cannot be empty");
    ;

    private String errorCode;
    private String errorMessage;

    ErrorConstant(String errorCode,String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
