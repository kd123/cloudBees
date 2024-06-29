package com.booking.Service.impl;

import com.booking.Service.BookingService;
import com.booking.Service.ReceiptService;
import com.booking.Service.SeatService;
import com.booking.Service.UserService;
import com.booking.constants.ErrorConstant;
import com.booking.converter.BookingRequestToBookingDetailConverter;
import com.booking.entity.Ticket;
import com.booking.entity.User;
import com.booking.exception.InvalidRequestException;
import com.booking.model.ApiResponse;
import com.booking.model.BookingRequest;
import com.booking.model.ReceiptDetail;
import com.booking.model.UserDetails;
import com.booking.repository.TicketRepository;
import com.booking.validator.BookingValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {
    private static final int price = 20;
    @Autowired
    private UserService userService;
    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private BookingRequestToBookingDetailConverter bookingDetailConverter;

    @Autowired
    private TicketRepository bookingRepository;

    @Autowired
    private SeatService seatService;

    @Override
    public ApiResponse<ReceiptDetail> bookNewTicket(BookingRequest bookingRequest) {
        try {
            if(Objects.isNull(bookingRequest)){
                log.error("Booking Request is null {}",ErrorConstant.BAD_REQUEST_INVALID_DATA.getErrorMessage());
                throw new InvalidRequestException(ErrorConstant.BAD_REQUEST_INVALID_INPUT.getErrorCode(),
                        ErrorConstant.BAD_REQUEST_INVALID_INPUT.getErrorMessage());
            }
            UserDetails userDetails = bookingRequest.getUserDetails();
            User user = userService.createUserEntry(userDetails);
            Ticket bookingDetails = bookingDetailConverter.convert(bookingRequest,null);
            bookingDetails.setUserId(user.getId());
            ErrorConstant errorConstant = BookingValidator.isFromValid()
                    .and(BookingValidator.isToValid()).and(BookingValidator.isSectionTypeValid())
                    .and(BookingValidator.isUserIdValid()).and(BookingValidator.isPaymentModeValid())
                    .apply(bookingDetails);
            if(Objects.nonNull(errorConstant)){
                log.error("An error occurred while converting booking details data");
                throw new InvalidRequestException(errorConstant.getErrorCode(),errorConstant.getErrorMessage());
            }
            bookingDetails = bookTicket(bookingDetails);
            ReceiptDetail receiptDetail = receiptService.generateReceipt(bookingDetails,user);
            ApiResponse<ReceiptDetail> response = new ApiResponse<>();
            response.buildSuccessResponse(HttpStatus.OK.value(), "Ticket is booked",receiptDetail);
            return response;
        } catch (InvalidRequestException ie){
            throw ie;
        } catch (Exception ex){
            log.error("An error has occurred while booking ticket {}",ErrorConstant.BAD_REQUEST_INVALID_DATA.getErrorMessage());
            throw new InvalidRequestException(ErrorConstant.BAD_REQUEST_INVALID_INPUT.getErrorCode(),
                    ErrorConstant.BAD_REQUEST_INVALID_INPUT.getErrorMessage());
        }
    }

    private Ticket bookTicket(Ticket bookingDetail){
        bookingDetail.setPricePaid(bookingDetail.getNumberOfTicket()*price);
        List<Long> allocatedSeats = new ArrayList<>();
        allocatedSeats = seatService.allocateSeat(bookingDetail.getNumberOfTicket(), bookingDetail.getSectionType());
        bookingDetail.setAllocatedSeats(allocatedSeats);
        bookingDetail = bookingRepository.save(bookingDetail);
        seatService.modifySeats(allocatedSeats,bookingDetail.getSectionType());
        log.info("Ticket Booked successfully with ticketId and SeatNumber {},{}", bookingDetail.getId(),allocatedSeats);
        return bookingDetail;

    }
}
