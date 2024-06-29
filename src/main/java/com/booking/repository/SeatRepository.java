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
    @Query(value = "SELECT * FROM  seat where sectionType= :sectionType and isAvailable=true limit:numberOfSeat",nativeQuery = true)
    Optional<List<Seat>> findBySectionTypeAndIsAvailableTrue(@Param("numberOfSeat") int numberOfSeat,
                                                                   @Param("sectionType") SectionType sectionType);

    @Modifying(clearAutomatically = true)
    @Query("update seat s set s.isAvailable= false  where s.seatNumber = :seatNumber and s.sectionType = :sectionType")
    void updateSeatBySeatNumberAndSectionType(long seatNumber, SectionType sectionType);
}
