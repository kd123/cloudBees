package com.booking.converter;

import com.booking.entity.Ticket;
import com.booking.model.BookingRequest;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BookingRequestToBookingDetailConverter implements Converter<BookingRequest, Ticket, Ticket> {

    @Override
    public Ticket convert(BookingRequest input, Ticket existing){
        if(Objects.isNull(input)){
            return null;
        }
        if(Objects.isNull(existing)){
            existing = new Ticket();
        }
        existing.setDestination(input.getTo());
        existing.setSource(input.getFrom());
        existing.setSectionType(input.getSectionType());
        existing.setPaymentMode(input.getPaymentMode());
        existing.setActive(true);
        existing.setNumberOfTicket(input.getTotalTicket());
        return existing;
    }
}
