package com.booking.Service;

import com.booking.entity.Seat;
import com.booking.model.enums.SectionType;

import java.util.List;

public interface SeatService {

    List<Long> allocateSeat(int numberOfSeat, SectionType sectionType);
    void modifySeats(List<Long> seatList,SectionType sectionType);
}
