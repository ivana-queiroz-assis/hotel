package com.alten.hotel.service.validation.date;

import com.alten.hotel.exception.DateBookingException;
import com.alten.hotel.model.dto.BookingDto;
import com.alten.hotel.model.validation.ValidationStep;

import java.time.temporal.ChronoUnit;

import static com.alten.hotel.util.AppConstants.MSG_BOOK_LIMIT_EXCEED;


public class LimitDaysMaxReservationValidation extends ValidationStep<BookingDto> {

    public LimitDaysMaxReservationValidation(ValidationStep<BookingDto> next) {
        super(next);
    }

    @Override
    public Boolean verify(BookingDto bookingDto) {
        Long diffDays = ChronoUnit.DAYS.between(bookingDto.getStartDate(), bookingDto.getEndDate());
        if( diffDays > 30){
            throw new DateBookingException(MSG_BOOK_LIMIT_EXCEED);
        }
        return next.verify(bookingDto);
    }
}