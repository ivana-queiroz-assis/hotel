package com.alten.hotel.service.validation.date;

import com.alten.hotel.exception.DateBookingException;
import com.alten.hotel.model.dto.BookingDto;
import com.alten.hotel.model.validation.ValidationStep;

import java.time.LocalDate;

import static com.alten.hotel.util.AppConstants.MSG_ERROR_RESERVATION_NOT_SCHEDULE_IN_ADVANCE;


public class StarterDateReservationValidation extends ValidationStep<BookingDto> {

    public StarterDateReservationValidation(ValidationStep<BookingDto> next) {
        super(next);
    }

    @Override
    public Boolean verify(BookingDto bookingDto) {

        if (!bookingDto.getStartDate().isAfter(LocalDate.now())){
            throw new DateBookingException(MSG_ERROR_RESERVATION_NOT_SCHEDULE_IN_ADVANCE);
        }
        return next.verify(bookingDto);
    }
}