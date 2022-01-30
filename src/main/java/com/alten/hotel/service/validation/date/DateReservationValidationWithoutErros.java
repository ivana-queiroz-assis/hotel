package com.alten.hotel.service.validation.date;

import com.alten.hotel.model.dto.BookingDto;
import com.alten.hotel.model.validation.ValidationStep;


public class DateReservationValidationWithoutErros extends ValidationStep<BookingDto> {

    public DateReservationValidationWithoutErros() {
        super(null);
    }

    @Override
    public Boolean verify(BookingDto bookingDto) {
        return true;
    }
}