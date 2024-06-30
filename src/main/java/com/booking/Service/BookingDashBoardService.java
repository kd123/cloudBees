package com.booking.Service;


import com.booking.model.ApiResponse;
import com.booking.model.ReceiptDetail;
import com.booking.model.UserSeatDetails;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookingDashBoardService {

    public ApiResponse<ReceiptDetail> getBookingReceipt(long ticketId);
    public void removeUserFromTrain(long userId);
    ApiResponse<List<UserSeatDetails>> getUsersAndAllocatedSeatBySectionType(String sectionType);

    ApiResponse<UserSeatDetails> updateUserSeat(long userId, String seats);
}
