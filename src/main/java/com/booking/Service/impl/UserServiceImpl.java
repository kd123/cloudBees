package com.booking.Service.impl;

import com.booking.Service.UserService;
import com.booking.constants.ErrorConstant;
import com.booking.converter.UserDetailsToUserConverter;
import com.booking.entity.User;
import com.booking.exception.InvalidRequestException;
import com.booking.model.UserDetails;
import com.booking.repository.UserRepository;
import com.booking.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDetailsToUserConverter userConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUserEntry(UserDetails userDetails) {
        User user = null;
        try {
            ErrorConstant errorConstant = UserValidator.isInputValid()
                    .and(UserValidator.isEmailValid())
                    .and(UserValidator.isFirstNameValid())
                    .and(UserValidator.isLastNameValid())
                    .apply(userDetails);
            if(Objects.nonNull(errorConstant)){
                log.error("An error occurred while creating user data");
                throw new InvalidRequestException(errorConstant.getErrorCode(),errorConstant.getErrorMessage());
            }
             user = userRepository.findByEmail(userDetails.getEmail());
            if(Objects.nonNull(user.getId())){
                log.info("User is already Exists with userId {}", user.getId());
                return user;
            }
            user = userConverter.convert(userDetails,null);
            user = userRepository.save(user);
            log.info("User create with UserId {}", user.getId());
            return user;
        }catch (Exception e){
            log.error("An error occurred while creating user data of userEmail {}", userDetails.getEmail());
            throw new InvalidRequestException(ErrorConstant.BAD_REQUEST_INVALID_INPUT.getErrorCode(),
                    ErrorConstant.BAD_REQUEST_INVALID_INPUT.getErrorMessage());
        }
    }
}