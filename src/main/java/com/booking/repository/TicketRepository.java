package com.booking.repository;


import com.booking.entity.Ticket;
import com.booking.model.enums.SectionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    Ticket findById(long id);
    List<Ticket> findByUserId(long userId);
    //List<Ticket> findAll();
    void deleteByUserId(long userId);

    List<Ticket> findBySectionType(SectionType type);

    @Modifying(clearAutomatically = true)
    @Query(value = "update Ticket t set t.allocatedSeats = :seat  where t.id = :id ",nativeQuery = true)
    void updateSeatForUser(@Param("id")Long id, @Param("seat") String seat);

}
