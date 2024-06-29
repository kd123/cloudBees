package com.booking.validator;

import com.booking.constants.ErrorConstant;
import com.booking.entity.Ticket;
import io.micrometer.common.util.StringUtils;
import org.springframework.util.ObjectUtils;

import java.util.Objects;
import java.util.function.Function;

public interface BookingValidator extends Function<Ticket, ErrorConstant> {
    public static final int maxNumberOfTicket = 5;

    static BookingValidator isInputValid(){
        return bookingRequest -> Objects.isNull(bookingRequest) ? ErrorConstant.BAD_REQUEST_INVALID_DATA:null;
    }

    static BookingValidator isFromValid(){
        return bookingRequest -> StringUtils.isBlank(bookingRequest.getFrom()) ? ErrorConstant.BAD_REQUEST_EMPTY_FROM : null;
    }

    static BookingValidator isToValid(){
        return bookingRequest -> StringUtils.isBlank(bookingRequest.getTo()) ? ErrorConstant.BAD_REQUEST_EMPTY_TO : null;
    }

    static BookingValidator isPaymentModeValid(){
        return bookingRequest -> ObjectUtils.isEmpty(bookingRequest.getPaymentMode()) ? ErrorConstant.BAD_REQUEST_EMPTY_PAYMENT_MODE : null;
    }

    static BookingValidator isSectionTypeValid(){
        return bookingRequest -> ObjectUtils.isEmpty(bookingRequest.getSectionType()) ? ErrorConstant.BAD_REQUEST_EMPTY_SECTION_TYPE : null;
    }
    static BookingValidator isUserIdValid(){
        return bookingRequest -> ObjectUtils.isEmpty(bookingRequest.getSectionType()) ? ErrorConstant.BAD_REQUEST_EMPTY_SECTION_TYPE : null;
    }
    static BookingValidator isNumberOfTicketValid(){
        return bookingRequest -> ObjectUtils.isEmpty(bookingRequest.getNumberOfTicket()) || bookingRequest.getNumberOfTicket()>maxNumberOfTicket  ? ErrorConstant.BAD_REQUEST_EMPTY_SECTION_TYPE : null;
    }

    default BookingValidator and(BookingValidator other){
        return bookingRequest -> {
            ErrorConstant errorConstant = this.apply(bookingRequest);
            return Objects.isNull(errorConstant) ? other.apply(bookingRequest) : errorConstant;
        };
    }

}
