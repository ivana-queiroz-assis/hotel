package com.alten.hotel.service.validation.date;

import com.alten.hotel.exception.DateBookingException;
import com.alten.hotel.model.dto.BookingDto;
import com.alten.hotel.model.validation.ValidationStep;

import static com.alten.hotel.util.AppConstants.MSG_ERROR_START_DATE_BIGGER_END_DATE;


public class StartDateBiggerThanEndDateValidation extends ValidationStep<BookingDto> {

    public StartDateBiggerThanEndDateValidation(ValidationStep<BookingDto> next) {
        super(next);
    }

    @Override
    public Boolean verify(BookingDto bookingDto) {
        if (!bookingDto.getStartDate().isBefore(bookingDto.getEndDate())){
            throw new DateBookingException(MSG_ERROR_START_DATE_BIGGER_END_DATE);
        }
        return next.verify(bookingDto);
    }
}
