package com.alten.hotel.service.validation.date;

import com.alten.hotel.data.BookingDtoMockData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class DateReservationValidationOfDateServiceTest {

    @InjectMocks
    private DateReservationValidationOfDateService dateReservationValidationOfDateService;

    @Test
    void givenBookingValid_whenValidateDates_thenIsValid(){
        Boolean result = dateReservationValidationOfDateService.validateDate(BookingDtoMockData.mockBookingDtoValid());

        assertTrue(result);
    }

}