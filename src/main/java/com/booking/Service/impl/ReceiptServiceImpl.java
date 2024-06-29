package com.booking.Service.impl;

import com.booking.Service.ReceiptService;
import com.booking.entity.Ticket;
import com.booking.entity.User;
import com.booking.model.ReceiptDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReceiptServiceImpl implements ReceiptService {

    public ReceiptDetail generateReceipt(Ticket bookingDetail, User user){
        return null;
    }
}
