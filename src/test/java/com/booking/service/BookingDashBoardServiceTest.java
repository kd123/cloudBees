package com.booking.service;

import com.booking.Service.*;
import com.booking.Service.impl.*;
import com.booking.entity.Ticket;
import com.booking.model.ApiResponse;
import com.booking.model.UserSeatDetails;
import com.booking.model.enums.SectionType;
import com.booking.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookingDashBoardServiceTest {

    @InjectMocks
    BookingDashBoardServiceImpl bookingDashBoardService;

    @Mock
    TicketRepository ticketRepository;
    @Mock
    UserServiceImpl userService;
    @Mock
    ReceiptServiceImpl receiptService;

    @Mock
    BookingServiceImpl bookingService;

    @Mock
    SeatServiceImpl seatService;


    @Test
    public void removeUserFromTrainTest(){
        doNothing().when(bookingService).deleteTicketByUserId(anyLong());
        doNothing().when(userService).removeUserFromTrain(anyLong());
        bookingDashBoardService.removeUserFromTrain(anyLong());
        verify(bookingService, times(1)).deleteTicketByUserId(anyLong());
        verify(userService, times(1)).removeUserFromTrain(anyLong());
    }

    @Test
    public void getUsersAndAllocatedSeatBySectionType(){
        List<UserSeatDetails> userSeatDetailsList = new ArrayList<>();
        long l =1;
        String seat = "1,2,3";
        SectionType type = SectionType.A;
        userSeatDetailsList.add(new UserSeatDetails(1,seat));
        List<Ticket> bookingDetails = new ArrayList<>();
        Ticket ticket = new Ticket();
        ticket.setAllocatedSeats(seat);
        ticket.setUserId(l);
        ticket.setSectionType(type);
        bookingDetails.add(ticket);

        ApiResponse<List<UserSeatDetails>> apiResponse = new ApiResponse<>();
        apiResponse.buildSuccessResponse(HttpStatus.OK.value(), "User seat details", userSeatDetailsList);
        when(ticketRepository.findBySectionType(type)).thenReturn(bookingDetails);
        assertEquals(apiResponse.getData().size(),bookingDashBoardService.getUsersAndAllocatedSeatBySectionType(type.name()).getData().size());
    }
}
