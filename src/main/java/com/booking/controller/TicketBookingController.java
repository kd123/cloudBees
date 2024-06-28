package com.booking.controller;

import com.booking.Service.BookingService;
import com.booking.model.BookingRequest;
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
    public ResponseEntity<String> bookTicket(@RequestBody BookingRequest bookingRequest){
        bookingService.bookNewTicket(bookingRequest);
        return new ResponseEntity<>("Ticket Booked successfully", HttpStatus.OK);
    }

}
