package com.booking.model;

import com.booking.entity.User;
import com.booking.model.enums.PaymentMode;
import com.booking.model.enums.SectionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingRequest {
    private String from;
    private String to;
    private UserDetails userDetails;
    private PaymentMode paymentMode;
    private SectionType sectionType;
    private int totalTicket=1;
}
