package com.booking.controller;

import com.booking.Service.BookingService;
import com.booking.model.ApiResponse;
import com.booking.model.BookingRequest;
import com.booking.model.ReceiptDetail;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class TicketBookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/newBooking")
    public ResponseEntity<ApiResponse<ReceiptDetail>> bookTicket(@RequestBody BookingRequest bookingRequest){
        return new ResponseEntity<>(bookingService.bookNewTicket(bookingRequest), HttpStatus.OK);
    }

}
