package com.booking.service;


import com.booking.Service.impl.UserServiceImpl;
import com.booking.converter.UserDetailsToUserConverter;
import com.booking.entity.User;
import com.booking.model.UserDetails;
import com.booking.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserDetailsToUserConverter userConverter;

    @Test
    public void createUserEntryTest(){
        User user = new User();
        long l = 1;
        user.setId(l);
        user.setFirstName("kd");
        UserDetails userDetails = new UserDetails("kd","gupta","abc@gmail.com");
        when(userConverter.convert(userDetails,null)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(userService.createUserEntry(userDetails).getFirstName(),user.getFirstName());
    }

    @Test
    public void getUserByUserIdTest(){
        User user = new User();
        long l = 1;
        user.setId(l);
        user.setFirstName("kd");
        when(userRepository.findById(l)).thenReturn(user);
        assertEquals(userService.getUserByUserId(l).getId(),user.getId());
    }

    @Test
    public void removeUserFromTrainTest(){
        doNothing().when(userRepository).deleteById(anyLong());
        userService.removeUserFromTrain(anyLong());
        verify(userRepository, times(1)).deleteById(anyLong());
    }
}
