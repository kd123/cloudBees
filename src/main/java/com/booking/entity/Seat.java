package com.booking.entity;


import com.booking.model.enums.SectionType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Seat {

    @Id
    private Long seatNumber;
    private SectionType sectionType;
    private boolean isAvailable = true;

}
