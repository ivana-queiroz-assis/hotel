package com.alten.hotel.service;

import com.alten.hotel.data.RoomDtoMockData;
import com.alten.hotel.model.dto.RoomDto;
import com.alten.hotel.repository.RoomBookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @InjectMocks
    private RoomService roomService;

    @Mock
    private RoomBookingRepository roomBookedRepository;

    @Test
    void givenIdRoomAlreadyReserved_whenCheckAvailability_thenReturnIsNotAvailable(){
        LocalDate now = LocalDate.now();
        LocalDate twoDaysAhead = now.plusDays(2);
        when(roomBookedRepository.countRoomBookedEntityByRoomEntityIdAndStartDateIsBeforeAndEndDateIsAfter(any(Long.class), any(LocalDate.class), any(LocalDate.class))).thenReturn(1);

        RoomDto response = roomService.checkAvailability(1L, now, twoDaysAhead);

        verify(roomBookedRepository, Mockito.times(1)).countRoomBookedEntityByRoomEntityIdAndStartDateIsBeforeAndEndDateIsAfter(any(Long.class), any(LocalDate.class), any(LocalDate.class));
        assertNotNull(response);
        assertFalse(response.getIsAvailable());
    }

    @Test
    void givenIdRoomNotReserved_whenCheckAvailability_thenReturnIsAvailable(){
        LocalDate now = LocalDate.now();
        LocalDate twoDaysAhead = now.plusDays(2);
        when(roomBookedRepository.countRoomBookedEntityByRoomEntityIdAndStartDateIsBeforeAndEndDateIsAfter(any(Long.class), any(LocalDate.class), any(LocalDate.class))).thenReturn(0);

        RoomDto response = roomService.checkAvailability(1L, now, twoDaysAhead);

        verify(roomBookedRepository, Mockito.times(1)).countRoomBookedEntityByRoomEntityIdAndStartDateIsBeforeAndEndDateIsAfter(any(Long.class), any(LocalDate.class), any(LocalDate.class));
        assertNotNull(response);
        assertTrue(response.getIsAvailable());
    }
}