package com.booking.service;

import com.booking.Service.BookingService;
import com.booking.Service.impl.BookingServiceImpl;
import com.booking.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookingServiceImplTest {

    @InjectMocks
    BookingServiceImpl bookingService;

    @Mock
    TicketRepository bookingRepository;

    @Test
    public void deleteTicketByUserIdTest(){
        doNothing().when(bookingRepository).deleteByUserId(anyLong());
        bookingService.deleteTicketByUserId(anyLong());
        verify(bookingRepository, times(1)).deleteByUserId(anyLong());
    }
}
