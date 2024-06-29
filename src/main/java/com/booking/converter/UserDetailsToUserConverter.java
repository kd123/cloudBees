package com.booking.converter;

import com.booking.entity.User;
import com.booking.model.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserDetailsToUserConverter implements Converter<UserDetails, User, User>{

    @Override
    public User convert(UserDetails input, User existing){
        if(Objects.isNull(input)) {
            return null;
        }
        if(Objects.isNull(existing)){
            existing = new User();
        }

        existing.setEmail(input.getEmail());
        existing.setFirstName(input.getFirstName());
        existing.setLastName(input.getLastName());
        return existing;
    }
}
