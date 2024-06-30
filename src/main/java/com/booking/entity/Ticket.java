package com.booking.entity;

import com.booking.model.enums.PaymentMode;
import com.booking.model.enums.SectionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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
    private String source;
    private String destination;
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
    @Enumerated(EnumType.STRING)
    private SectionType sectionType;
    private int numberOfTicket;
    private int pricePaid;
    private String allocatedSeats;
    private boolean isActive;
}
