package com.alten.hotel.service.validation.date;

import com.alten.hotel.data.BookingDtoMockData;
import com.alten.hotel.exception.DateBookingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.alten.hotel.util.AppConstants.MSG_ERROR_RESERVATION_NOT_SCHEDULE_IN_ADVANCE;
import static com.alten.hotel.util.AppConstants.MSG_ERROR_START_DATE_BIGGER_END_DATE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StarterDateReservationValidationTest {

    @InjectMocks
    private StarterDateReservationValidation starterDateReservationValidation;

    @Test
    void givenBookingWithoutOneDayInAdvance_whenValidateDates_thenThrowDateException(){
        Throwable exception = assertThrows(DateBookingException.class, () -> {
            starterDateReservationValidation.verify(BookingDtoMockData.mockBookingDtoWithoutOneDayOfAdvance());
        });
        assertEquals(MSG_ERROR_RESERVATION_NOT_SCHEDULE_IN_ADVANCE, exception.getMessage());
    }
}