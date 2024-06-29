package com.booking.Service.impl;

import com.booking.Service.SeatService;
import com.booking.entity.Seat;
import com.booking.model.enums.SectionType;
import com.booking.repository.SeatRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SeatServiceImpl implements SeatService {

    private static final Logger log = LoggerFactory.getLogger(SeatServiceImpl.class);
    private static final long MAX_SEAT = 100;
    @Autowired
    SeatRepository seatRepository;

    @PostConstruct
    public void init(){
        initializeSeats();
    }

    @Override
    public List<Long> allocateSeat(int numberOfSeat, SectionType sectionType) {
        Optional<List<Seat>> optionalSeats = seatRepository.findBySectionTypeAndIsAvailableTrue(numberOfSeat, sectionType);
        List<Seat> seatList= new ArrayList<>();
        optionalSeats.ifPresent(seatList::addAll);
        return seatList.stream().map(Seat::getSeatNumber).collect(toList());
    }

    @Override
    public void modifySeats(List<Long> seatList, SectionType sectionType) {
        seatList.forEach(seatNumber->seatRepository.updateSeatBySeatNumberAndSectionType(seatNumber,sectionType));
        log.info("Seat Number got updated Successfully");
    }

    private void initializeSeats(){
        List<Seat> sectionAlist = Stream.iterate(1, i -> i + 1).map(k->generateSeatObject(k,SectionType.SECTION_A))
                .limit(MAX_SEAT).toList();
        List<Seat>sectionBlist = Stream.iterate(1, i -> i + 1).map(k->generateSeatObject(k,SectionType.SECTION_B))
                .limit(MAX_SEAT).toList();
        List<Seat>list = Stream.concat(sectionAlist.stream(),sectionBlist.stream()).toList();
        seatRepository.saveAll(list);

    }

    private Seat generateSeatObject(long seatNumber, SectionType sectionType){
        Seat seat = new Seat();
        seat.setSeatNumber(seatNumber);
        seat.setSectionType(sectionType);
        seat.setAvailable(true);
        return seat;
    }
}
