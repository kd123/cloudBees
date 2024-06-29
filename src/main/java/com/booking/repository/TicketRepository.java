package com.booking.repository;


import com.booking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    Ticket findById(long id);
    List<Ticket> findByUserId(long userId);
    List<Ticket> findAll();
}
