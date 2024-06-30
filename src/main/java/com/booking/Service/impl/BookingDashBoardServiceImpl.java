package com.booking.Service.impl;

import com.booking.Service.*;
import com.booking.constants.ErrorConstant;
import com.booking.entity.Ticket;
import com.booking.entity.User;
import com.booking.exception.DataNotFoundException;
import com.booking.exception.InvalidRequestException;
import com.booking.model.ApiResponse;
import com.booking.model.ReceiptDetail;
import com.booking.model.UserSeatDetails;
import com.booking.model.enums.SectionType;
import com.booking.repository.TicketRepository;
import com.booking.util.TicketBookingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class BookingDashBoardServiceImpl implements BookingDashBoardService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private SeatService seatService;


    @Override
    public ApiResponse<ReceiptDetail> getBookingReceipt(long ticketId) {
        Ticket bookingDetail = null;
        ReceiptDetail receiptDetail = new ReceiptDetail();
        try {
            bookingDetail = ticketRepository.findById(ticketId);
            if(Objects.isNull(bookingDetail)){
                log.error("No Booking Detail found for ticketId {}", ticketId);
                return null;
            }
            receiptDetail = receiptService.generateReceipt(bookingDetail,userService.getUserByUserId(bookingDetail.getUserId()));
            ApiResponse<ReceiptDetail> response = new ApiResponse<>();
            response.buildSuccessResponse(HttpStatus.OK.value(), "Ticket is booked",receiptDetail);
            return response;
        }catch (Exception e){
            log.error("Error while fetching Receipt of user for ticketId {}",ticketId);
            throw new DataNotFoundException(ErrorConstant.DATA_NOT_FOUND.getErrorCode(),
                    ErrorConstant.DATA_NOT_FOUND.getErrorMessage());
        }
    }

    @Override
    public void removeUserFromTrain(long userId){
        try {
            bookingService.deleteTicketByUserId(userId);
            userService.removeUserFromTrain(userId);
            log.info("user got removed from train successfully of userId {}", userId);
        }catch (InvalidRequestException e){
            log.error("Error while removing user from train for userId {}", userId);
            throw new InvalidRequestException(ErrorConstant.BAD_REQUEST_INVALID_DATA.getErrorCode(),
                    ErrorConstant.BAD_REQUEST_INVALID_BOOKING_ID.getErrorMessage());
        }
    }
    @Override
    public ApiResponse<List<UserSeatDetails>> getUsersAndAllocatedSeatBySectionType(String sectionType){
        List<UserSeatDetails> userSeatDetailsList = new ArrayList<>();
        List<Ticket> bookingDetails = null;
        SectionType type = SectionType.valueOf(sectionType.toUpperCase(Locale.ROOT));
        try {
           bookingDetails = ticketRepository.findBySectionType(type);
           userSeatDetailsList = bookingDetails.stream()
                   .map(b -> new UserSeatDetails(b.getUserId(),b.getAllocatedSeats())).toList();
           ApiResponse<List<UserSeatDetails>> apiResponse = new ApiResponse<>();
           apiResponse.buildSuccessResponse(HttpStatus.OK.value(), "User seat details", userSeatDetailsList);
           return apiResponse;
        }catch (Exception e){
            log.error("Error while fetching user seat details for sectionType {}", sectionType);
            throw new DataNotFoundException(ErrorConstant.DATA_NOT_FOUND.getErrorCode(),
                    ErrorConstant.DATA_NOT_FOUND.getErrorMessage());
        }
    }

    @Override
   public ApiResponse<UserSeatDetails> updateUserSeat(long userId, String seats){
        User user = null;
        Ticket bookingDetail = null;
        try {
            bookingDetail = bookingService.getBookingDetailByUserId(userId);
            if(Objects.isNull(bookingDetail)){
                throw new DataNotFoundException(ErrorConstant.DATA_NOT_FOUND.getErrorCode(),
                        ErrorConstant.DATA_NOT_FOUND.getErrorMessage());
            }
            List<Integer> availableSeatBySeatNumbers = seatService.getSeatsBySeatNumbers(seats,bookingDetail.getSectionType());
            if(availableSeatBySeatNumbers.size()
                    !=
                    TicketBookingUtil.convertCommaSeparatedStringToArrayList(bookingDetail.getAllocatedSeats()).size()
            ){
                log.error("number of allocated Seats to user and number of given seat for modification are not equal ");
                throw new InvalidRequestException(ErrorConstant.BAD_REQUEST_INVALID_INPUT.getErrorCode(),
                        ErrorConstant.BAD_REQUEST_INVALID_INPUT.getErrorMessage());
            }
            seatService.modifySeats(
                    TicketBookingUtil.convertCommaSeparatedStringToArrayList(bookingDetail.getAllocatedSeats()),
                    bookingDetail.getSectionType()
            );
            ticketRepository.updateSeatForUser(bookingDetail.getId(),seats);
            bookingDetail.setAllocatedSeats(seats);
            ApiResponse<UserSeatDetails> response = new ApiResponse<>();
            UserSeatDetails userSeatDetails = new UserSeatDetails(bookingDetail.getUserId(), seats);
            response.buildSuccessResponse(HttpStatus.OK.value(), "User seat updated for userId ", userSeatDetails);
            return response;

        }catch (DataNotFoundException | InvalidRequestException de){
            throw de;
        } catch (Exception e){
            log.error("error while modifying ticket for userId and seats {} => {} ",userId,seats);
            throw new InvalidRequestException(ErrorConstant.BAD_REQUEST_INVALID_INPUT.getErrorCode(),
                    ErrorConstant.BAD_REQUEST_INVALID_INPUT.getErrorMessage());
        }
    }
}
