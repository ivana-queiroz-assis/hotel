package com.alten.hotel.service.validation.date;

import com.alten.hotel.model.dto.BookingDto;

public interface BookingValidationOfDateService {
    Boolean validateDate(BookingDto bookingDto);
}
