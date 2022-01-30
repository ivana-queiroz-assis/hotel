package com.alten.hotel.controller;

import com.alten.hotel.data.BookingDtoMockData;
import com.alten.hotel.data.RoomDtoMockData;
import com.alten.hotel.model.dto.BookingDto;
import com.alten.hotel.model.generic.GenericResponse;
import com.alten.hotel.service.BookingService;
import com.alten.hotel.service.RoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static com.alten.hotel.util.AppConstants.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomControllerTest {

    @InjectMocks
    private RoomController roomController;

    @Mock
    private RoomService roomService;

    @Mock
    private BookingService bookingService;

    @Test
    void givenIdRoomAndDates_whenGetRoomAvailability_thenReturnAvailability(){
        when(roomService.checkAvailability(any(), any(), any())).thenReturn(RoomDtoMockData.mockRoomDtoAvailable());

        GenericResponse response = roomController.getRoom(1L, "2022-02-03", "2022-02-05");

        verify(roomService, Mockito.times(1)).checkAvailability(any(Long.class), any(LocalDate.class), any(LocalDate.class));
        assertEquals(HttpStatus.OK.toString(),response.getCode());
        assertEquals(SUCCESS,response.getStatus());
        assertNotNull(response.getData());
    }

    @Test
    void givenIdRoomAndBooking_whenPostBooking_thenIsCreated(){
        when(bookingService.create(any(), any())).thenReturn(BookingDtoMockData.mockBookingDtoValid());

        GenericResponse response = roomController.postBooking(1L, BookingDtoMockData.mockBookingDtoValid());

        verify(bookingService, Mockito.times(1)).create(any(Long.class), any(BookingDto.class));
        assertEquals(HttpStatus.CREATED.toString(),response.getCode());
        assertEquals(SUCCESS,response.getStatus());
        assertNotNull(response.getData());
    }
}