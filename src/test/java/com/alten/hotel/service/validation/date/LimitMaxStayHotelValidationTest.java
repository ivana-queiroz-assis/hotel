package com.alten.hotel.service.validation.date;

import com.alten.hotel.data.BookingDtoMockData;
import com.alten.hotel.exception.DateBookingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static com.alten.hotel.util.AppConstants.MSG_BOOK_MAX_LIMIT_EXCEED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class LimitMaxStayHotelValidationTest {

    @InjectMocks
    private LimitMaxStayHotelValidation limitMaxStayHotelValidation;

    @Test
    void givenBookingWithMaxStayInHotel_whenValidateDates_thenThrowDateException(){
        Throwable exception = assertThrows(DateBookingException.class, () -> {
            limitMaxStayHotelValidation.verify(BookingDtoMockData.mockBookingDtoMaxStayInHotel());
        });
        assertEquals(MSG_BOOK_MAX_LIMIT_EXCEED, exception.getMessage());
    }

    @Test
    void givenLimitMaxOf3DaysOfStayInHotel_whenValidateDates_thenCallNext(){
        ReflectionTestUtils.setField(limitMaxStayHotelValidation, "next", new DateReservationValidationWithoutErros());
        limitMaxStayHotelValidation.verify(BookingDtoMockData.mockBookingDto3DaysOfStayInHotel());
    }
}