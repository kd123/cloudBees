package com.booking.Service;

import com.booking.entity.Ticket;
import com.booking.entity.User;
import com.booking.model.ReceiptDetail;
import org.springframework.stereotype.Service;

public interface ReceiptService {

    public ReceiptDetail generateReceipt(Ticket bookingDetail, User user);
}
