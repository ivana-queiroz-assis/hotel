package com.alten.hotel.service.validation.conflits;

import com.alten.hotel.data.RoomEntityMockData;
import com.alten.hotel.exception.ConflictBookingException;
import com.alten.hotel.repository.RoomBookingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConflitsReservationServiceTest {

    @InjectMocks
    private ConflitsReservationService conflitsReservationService;

    @Mock
    private RoomBookingRepository roomBookedRepository;

    @Test
    void givenRoomAlreadyReserved_whenCheckingForConflict_thenThrowException(){
        Assertions.assertThrows(ConflictBookingException.class, () -> {
            LocalDate now = LocalDate.now();
            LocalDate twoDaysAhead = now.plusDays(2);
            when(roomBookedRepository.countRoomBookedEntityByRoomEntityIdAndStartDateIsBeforeAndEndDateIsAfter(any(Long.class), any(LocalDate.class), any(LocalDate.class))).thenReturn(3);

            conflitsReservationService.checkConflicts(RoomEntityMockData.mockBookingEntity(), now, twoDaysAhead);

            verify(roomBookedRepository, Mockito.times(1)).countRoomBookedEntityByRoomEntityIdAndStartDateIsBeforeAndEndDateIsAfter(any(Long.class), any(LocalDate.class), any(LocalDate.class));
        });
    }

    @Test
    void givenRoomNotReserved_whenCheckingForConflict_thenDontThrowException(){

        LocalDate now = LocalDate.now();
        LocalDate twoDaysAhead = now.plusDays(2);
        when(roomBookedRepository.countRoomBookedEntityByRoomEntityIdAndStartDateIsBeforeAndEndDateIsAfter(any(Long.class), any(LocalDate.class), any(LocalDate.class))).thenReturn(0);

        conflitsReservationService.checkConflicts(RoomEntityMockData.mockBookingEntity(), now, twoDaysAhead);

        verify(roomBookedRepository, Mockito.times(1)).countRoomBookedEntityByRoomEntityIdAndStartDateIsBeforeAndEndDateIsAfter(any(Long.class), any(LocalDate.class), any(LocalDate.class));

    }

}