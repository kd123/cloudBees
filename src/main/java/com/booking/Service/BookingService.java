package com.booking.Service;

import com.booking.entity.Ticket;
import com.booking.model.ApiResponse;
import com.booking.model.BookingRequest;
import com.booking.model.ReceiptDetail;
import org.springframework.stereotype.Service;

public interface BookingService {

    public ApiResponse<ReceiptDetail> bookNewTicket(BookingRequest bookingRequest);
    public void deleteTicketByUserId(long userId);
    public Ticket getBookingDetailByUserId(long userId);
}
