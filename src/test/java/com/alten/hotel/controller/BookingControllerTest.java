package com.alten.hotel.controller;

import com.alten.hotel.data.BookingDtoMockData;
import com.alten.hotel.model.dto.BookingDto;
import com.alten.hotel.model.generic.GenericResponse;
import com.alten.hotel.service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static com.alten.hotel.util.AppConstants.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    @InjectMocks
    private BookingController bookingController;

    @Mock
    private BookingService bookingService;

    @Test
    void givenExistingBooking_whenDeletingBooking_thenHttpStatus204(){
        GenericResponse response = bookingController.deleteBooking(12L);

        verify(bookingService, Mockito.times(1)).delete(any(Long.class));
        assertEquals(HttpStatus.NO_CONTENT.toString(),response.getCode());
        assertEquals(SUCCESS,response.getStatus());
    }

    @Test
    void givenExistingBooking_whenEditingBooking_thenHttpStatus200(){
        when(bookingService.editBooking(any(), any())).thenReturn(BookingDtoMockData.mockBookingDtoValid());

        GenericResponse response = bookingController.putBooking(12L, BookingDtoMockData.mockBookingDtoValid());

        verify(bookingService, Mockito.times(1)).editBooking(any(Long.class), any(BookingDto.class));
        assertEquals(HttpStatus.OK.toString(),response.getCode());
        assertNotNull(response.getData());
        assertEquals(SUCCESS,response.getStatus());
    }
}