package com.booking.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSeatDetails {
    private long userId;
    String allocatedSeat;

    public UserSeatDetails(Long userId, String allocatedSeats) {
        this.allocatedSeat = allocatedSeats;
        this.userId = userId;
    }
}
