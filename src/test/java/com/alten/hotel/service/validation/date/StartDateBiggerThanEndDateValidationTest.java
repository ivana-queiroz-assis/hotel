package com.alten.hotel.service.validation.date;

import com.alten.hotel.data.BookingDtoMockData;
import com.alten.hotel.exception.DateBookingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.alten.hotel.util.AppConstants.MSG_ERROR_START_DATE_BIGGER_END_DATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class StartDateBiggerThanEndDateValidationTest {

    @InjectMocks
    private StartDateBiggerThanEndDateValidation startDateBiggerThanEndDateValidation;

    @Test
    void givenBookingWithEndDateBiggerThanStartDate_whenValidateDates_thenThrowDateException(){
        Throwable exception = assertThrows(DateBookingException.class, () -> {
            startDateBiggerThanEndDateValidation.verify(BookingDtoMockData.mockBookingDtoEndDateBiggerThanStartDate());
        });
        assertEquals(MSG_ERROR_START_DATE_BIGGER_END_DATE, exception.getMessage());
    }
}