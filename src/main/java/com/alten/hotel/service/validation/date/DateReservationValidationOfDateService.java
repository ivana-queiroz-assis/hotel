package com.alten.hotel.service.validation.date;

import com.alten.hotel.model.dto.BookingDto;
import com.alten.hotel.model.validation.ValidationStep;
import org.springframework.stereotype.Service;

@Service
public class DateReservationValidationOfDateService implements BookingValidationOfDateService {

    @Override
    public Boolean validateDate(BookingDto bookingDto) {
        ValidationStep validationStep = new LimitMaxStayHotelValidation(
                new LimitDaysMaxReservationValidation(
                new StartDateBiggerThanEndDateValidation(
                new StarterDateReservationValidation(new DateReservationValidationWithoutErros()))));

        return validationStep.verify(bookingDto);
    }
}
