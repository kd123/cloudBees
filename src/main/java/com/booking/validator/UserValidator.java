package com.booking.validator;

import com.booking.constants.ErrorConstant;
import com.booking.model.UserDetails;
import io.micrometer.common.util.StringUtils;

import java.util.Objects;
import java.util.function.Function;

public interface UserValidator extends Function<UserDetails, ErrorConstant> {

    static UserValidator isInputValid(){
        return userDetails -> Objects.isNull(userDetails) ? ErrorConstant.BAD_REQUEST_INVALID_DATA:null;
    }

    static UserValidator isFirstNameValid(){
        return userDetails -> StringUtils.isBlank(userDetails.getFirstName()) ? ErrorConstant.BAD_REQUEST_INVALID_FIRST_NAME : null;
    }

    static UserValidator isLastNameValid(){
        return userDetails -> StringUtils.isBlank(userDetails.getLastName()) ? ErrorConstant.BAD_REQUEST_INVALID_LAST_NAME : null;
    }

    static UserValidator isEmailValid(){
        return userDetails -> StringUtils.isBlank(userDetails.getLastName()) ? ErrorConstant.BAD_REQUEST_EMPTY_EMAIL : null;
    }

    default UserValidator and(UserValidator other){
        return userDetails -> {
            ErrorConstant errorConstant = this.apply(userDetails);
            return Objects.isNull(errorConstant) ? other.apply(userDetails) : errorConstant;
        };
    }

}
