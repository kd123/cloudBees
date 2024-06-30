package com.booking.util;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TicketBookingUtilTest {

    @Test
    public void convertCommaSeparatedStringToArrayListTest(){
        List<Integer> seatList = Arrays.asList(1,2,3);
        String seats = "1,2,3";
        List<Integer> res = TicketBookingUtil.convertCommaSeparatedStringToArrayList(seats);
        assertEquals(seatList.get(0),res.get(0));
        assertEquals(seatList.size(), res.size());
        assertNotNull(res);

    }

    @Test
    public void convertListOfIntegerToCommaSeparatedStringTest(){
        List<Integer> seatList = Arrays.asList(1,2,3);
        String seats = "1,2,3";
        String res = TicketBookingUtil.convertListOfIntegerToCommaSeparatedString(seatList);
        assertEquals(seats,res);
        assertNotNull(res);

    }
}
