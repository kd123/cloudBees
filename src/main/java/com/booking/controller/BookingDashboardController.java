package com.booking.controller;


import com.booking.Service.BookingDashBoardService;
import com.booking.Service.BookingService;
import com.booking.model.ApiResponse;
import com.booking.model.BookingRequest;
import com.booking.model.ReceiptDetail;
import com.booking.model.UserSeatDetails;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/booking-dashboard")
public class BookingDashboardController {

    @Autowired
    private BookingDashBoardService dashBoardService;

    @GetMapping("/booking-receipt")
    public ResponseEntity<ApiResponse<ReceiptDetail>> bookTicket(@RequestParam long ticketId){
        return new ResponseEntity<>(dashBoardService.getBookingReceipt(ticketId), HttpStatus.OK);
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<String> removeUserFromTrain(@RequestParam long userId) {
        dashBoardService.removeUserFromTrain(userId);
        return new ResponseEntity<>("User got removed from train successfully", HttpStatus.OK);
    }

    @GetMapping("/user-seat")
    public ResponseEntity<ApiResponse<List<UserSeatDetails>>> getUsersAndAllocatedSeatBySectionType(@RequestParam String sectionType){
        return new ResponseEntity<>(dashBoardService.getUsersAndAllocatedSeatBySectionType(sectionType), HttpStatus.OK);
    }

    @PutMapping("/update-seat")
    public ResponseEntity<ApiResponse<UserSeatDetails>> updateSeat(@RequestParam long userId, @RequestParam String seats){
        return new ResponseEntity<>(dashBoardService.updateUserSeat(userId,seats), HttpStatus.OK);
    }
}
