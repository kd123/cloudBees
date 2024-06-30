package com.booking.Service;

import com.booking.entity.Seat;
import com.booking.model.enums.SectionType;

import java.util.List;

public interface SeatService {

    String allocateSeat(int numberOfSeat, SectionType sectionType);
    void modifySeats(List<Integer> seatList,SectionType sectionType);

    List<Integer> getSeatsBySeatNumbers(String seat, SectionType sectionType);
}
