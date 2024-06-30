package com.booking.repository;

import com.booking.Service.SeatService;
import com.booking.entity.Seat;
import com.booking.model.enums.SectionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {
    @Query(value = "SELECT * FROM  Seat where sectionType=:sectionType and available=true limit:numberOfSeat",nativeQuery = true)
    Optional<List<Seat>> findBySectionTypeAndIsAvailableTrue(@Param("numberOfSeat") int numberOfSeat,
                                                                   @Param("sectionType") SectionType sectionType);

    @Modifying(clearAutomatically = true)
    @Query(value = "update Seat s set s.available= false  where s.seatNumber=:seatNumber and s.sectionType = :sectionType",nativeQuery = true)
    void updateSeatBySeatNumberAndSectionType(@Param("seatNumber")int seatNumber, @Param("sectionType") SectionType sectionType);

    @Modifying(clearAutomatically = true)
    @Query(value = "update Seat s set s.available= false  where s.seatNumber in(:seatNumber) and s.sectionType = :sectionType",nativeQuery = true)
    void updateSeatBySeatNumbersAndSectionType(@Param("seatNumber")List<Integer> seatNumber, @Param("sectionType") SectionType sectionType);


    @Query(value = "SELECT * FROM  Seat where sectionType=:sectionType and seatNumber in (:seatNumber)",nativeQuery = true)
    Optional<List<Seat>> findBySectionTypeAndSeatNumbers(@Param("seatNumber") List<Integer> seatNumber,
                                                             @Param("sectionType") SectionType sectionType);

}
