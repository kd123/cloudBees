package com.booking.Service;

import com.booking.entity.User;
import com.booking.model.UserDetails;
import org.springframework.stereotype.Service;

public interface UserService {

    public User createUserEntry(UserDetails userDetails);
}
