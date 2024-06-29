package com.booking.entity;

import com.booking.model.enums.PaymentMode;
import com.booking.model.enums.SectionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String from;
    private String to;
    private PaymentMode paymentMode;
    private SectionType sectionType;
    private int numberOfTicket;
    private int pricePaid;
    private List<Long> allocatedSeats;
    private boolean isActive;
}
