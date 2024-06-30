package com.booking.service;

import com.booking.Service.ReceiptService;
import com.booking.Service.impl.ReceiptServiceImpl;
import com.booking.converter.BookingDetailsToBookingReceiptConverter;
import com.booking.entity.Ticket;
import com.booking.entity.User;
import com.booking.model.ReceiptDetail;
import com.booking.model.enums.SectionType;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReceiptServiceTest {

    @InjectMocks
    ReceiptServiceImpl receiptService;

    @Mock
    BookingDetailsToBookingReceiptConverter receiptConverter;

    @Test
    public void generateReceiptTest(){
        long l =1;
        String seat = "1,2,3";
        SectionType type = SectionType.A;
        Ticket ticket = new Ticket();
        ticket.setAllocatedSeats(seat);
        ticket.setUserId(l);
        ticket.setSectionType(type);

        User user = new User();
        user.setId(l);
        user.setFirstName("kd");
        ReceiptDetail receiptDetail = new ReceiptDetail();
        receiptDetail.setUserDetails(user);
        receiptDetail.setBookingDetails(ticket);

        when(receiptConverter.convert(ticket,null)).thenReturn(receiptDetail);
        assertEquals(ticket.getAllocatedSeats(),
                receiptService.generateReceipt(ticket,user).getBookingDetails().getAllocatedSeats());
        assertEquals(user.getFirstName(),
                receiptService.generateReceipt(ticket,user).getUserDetails().getFirstName());
    }
}
