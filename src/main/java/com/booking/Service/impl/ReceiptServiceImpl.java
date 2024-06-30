package com.booking.Service.impl;

import com.booking.Service.ReceiptService;
import com.booking.constants.ErrorConstant;
import com.booking.converter.BookingDetailsToBookingReceiptConverter;
import com.booking.entity.Ticket;
import com.booking.entity.User;
import com.booking.exception.InvalidRequestException;
import com.booking.model.ReceiptDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private BookingDetailsToBookingReceiptConverter receiptConverter;

    public ReceiptDetail generateReceipt(Ticket bookingDetail, User user) {
        ReceiptDetail receiptDetail = null;
        try {
            receiptDetail = receiptConverter.convert(bookingDetail, null);
            receiptDetail.setUserDetails(user);
            return receiptDetail;
        } catch (Exception e) {
            log.error("error while generating Booking Receipt {}", e.getMessage());
            throw new InvalidRequestException(ErrorConstant.BAD_REQUEST_INVALID_DATA.getErrorCode(),
                    ErrorConstant.BAD_REQUEST_INVALID_DATA.getErrorMessage());
        }
    }
}
