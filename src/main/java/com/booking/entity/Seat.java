package com.booking.entity;


import com.booking.model.enums.SectionType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Seat {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    private int seatNumber;
    @Enumerated(EnumType.STRING)
    private SectionType sectionType;
    private boolean available = true;

}
