package com.booking.Service;

import com.booking.model.BookingRequest;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {

    public void bookNewTicket(BookingRequest bookingRequest);
}
