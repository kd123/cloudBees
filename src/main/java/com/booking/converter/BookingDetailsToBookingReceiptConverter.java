package com.booking.converter;

import com.booking.entity.Ticket;
import com.booking.model.BookingRequest;
import com.booking.model.ReceiptDetail;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BookingDetailsToBookingReceiptConverter implements Converter<Ticket, ReceiptDetail, ReceiptDetail>{

    @Override
    public ReceiptDetail convert(Ticket input, ReceiptDetail existing) {
        if(Objects.isNull(input)) {
            return null;
        }
        if(Objects.isNull(existing)){
            existing = new ReceiptDetail();
        }
        existing.setBookingDetails(input);
        return existing;
    }
}
