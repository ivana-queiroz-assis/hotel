package com.alten.hotel.service.validation.date;

import com.alten.hotel.data.BookingDtoMockData;
import com.alten.hotel.exception.DateBookingException;
import com.alten.hotel.model.entity.RoomEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;

import static com.alten.hotel.util.AppConstants.MSG_BOOK_LIMIT_EXCEED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LimitDaysMaxReservationValidationTest {

    @InjectMocks
    private LimitDaysMaxReservationValidation limitDaysMaxReservationValidation;

    @Test
    void givenBookingWithMaxLimitDaysExceed_whenValidateDates_thenThrowDateException(){
        Throwable exception = assertThrows(DateBookingException.class, () -> {
            limitDaysMaxReservationValidation.verify(BookingDtoMockData.mockBookingDtoLimitMaxDaysExceed());
        });
        assertEquals(MSG_BOOK_LIMIT_EXCEED, exception.getMessage());
    }

    @Test
    void givenMaxLimitOf30DaysExceed_whenValidateDates_thenCallNext(){
        ReflectionTestUtils.setField(limitDaysMaxReservationValidation, "next", new DateReservationValidationWithoutErros());
        limitDaysMaxReservationValidation.verify(BookingDtoMockData.mockBookingDtoMaxLimit30DaysInAdvance());
    }
}