package com.booking.Service.impl;

import com.booking.Service.SeatService;
import com.booking.entity.Seat;
import com.booking.model.enums.SectionType;
import com.booking.repository.SeatRepository;
import com.booking.util.TicketBookingUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

@Service
@Slf4j
public class SeatServiceImpl implements SeatService {

    private static final long MAX_SEAT = 100;
    @Autowired
    SeatRepository seatRepository;

    @PostConstruct
    public void init(){
        initializeSeats();
    }

    @Override
    public String allocateSeat(int numberOfSeat, SectionType sectionType) {
        Optional<List<Seat>> optionalSeats = seatRepository.findBySectionTypeAndIsAvailableTrue(numberOfSeat, sectionType);
        List<Seat> seatList= new ArrayList<>();
        optionalSeats.ifPresent(seatList::addAll);
        return StringUtils.join(seatList,",");
    }

    @Override
    public void modifySeats(List<Integer> seatList, SectionType sectionType) {
        seatList.forEach(seatNumber->seatRepository.updateSeatBySeatNumberAndSectionType(seatNumber,sectionType));
        log.info("Seat Number got updated Successfully");
    }

    private void initializeSeats(){
        List<Seat> sectionAlist = Stream.iterate(1, i -> i + 1).map(k->generateSeatObject(k,SectionType.A))
                .limit(MAX_SEAT).toList();
        List<Seat>sectionBlist = Stream.iterate(1, i -> i + 1).map(k->generateSeatObject(k,SectionType.B))
                .limit(MAX_SEAT).toList();
        List<Seat>list = Stream.concat(sectionAlist.stream(),sectionBlist.stream()).toList();
        seatRepository.saveAll(list);

    }

    private Seat generateSeatObject(int seatNumber, SectionType sectionType){
        log.info("Seat Number {} ============================== ", seatNumber);
        Seat seat = new Seat();
        seat.setSeatNumber(seatNumber);
        seat.setSectionType(sectionType);
        seat.setAvailable(true);
        return seat;
    }

    @Override
    public List<Integer> getSeatsBySeatNumbers(String seat, SectionType sectionType){
        List<Integer> seats = new ArrayList<>();
        Optional<List<Seat>> optionalSeats = seatRepository.findBySectionTypeAndSeatNumbers(
                TicketBookingUtil.convertCommaSeparatedStringToArrayList(seat), sectionType );
        optionalSeats.ifPresent(seatList -> seatList.stream()
                .filter(Seat::isAvailable)
                .map(s->seats.add(s.getSeatNumber())));
        return seats;

    }
}
