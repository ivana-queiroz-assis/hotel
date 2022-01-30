package com.alten.hotel.service;

import com.alten.hotel.data.BookingDtoMockData;
import com.alten.hotel.data.BookingEntityMockData;
import com.alten.hotel.data.RoomBookingEntityMockData;
import com.alten.hotel.data.RoomEntityMockData;
import com.alten.hotel.exception.BookingNotFoundException;
import com.alten.hotel.mapper.BookingMapper;
import com.alten.hotel.model.dto.BookingDto;
import com.alten.hotel.model.entity.BookingEntity;
import com.alten.hotel.model.entity.RoomBookingEntity;
import com.alten.hotel.model.entity.RoomEntity;
import com.alten.hotel.repository.BookingRepository;
import com.alten.hotel.repository.RoomBookingRepository;
import com.alten.hotel.repository.RoomRepository;
import com.alten.hotel.service.validation.conflits.ConflitsReservationService;
import com.alten.hotel.service.validation.date.DateReservationValidationOfDateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static com.alten.hotel.util.AppConstants.MSG_BOOKING_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private RoomBookingRepository roomBookingRepository;

    @Mock
    private DateReservationValidationOfDateService dateReservationValidation;

    @Mock
    private ConflitsReservationService conflitsReservationService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BookingMapper bookingMapper;

    @Captor
    private ArgumentCaptor<RoomBookingEntity> roomBookingCaptor;


    @Test
    void givenIdRoomAndBookingInfo_whenCreateBooking_thenReturnBookingDto(){
        when(dateReservationValidation.validateDate(any(BookingDto.class))).thenReturn(Boolean.TRUE);
        when(roomRepository.findById(any(Long.class))).thenReturn(Optional.of(RoomEntityMockData.mockBookingEntity()));
        when(bookingMapper.toBookingDto(any(RoomBookingEntity.class))).thenReturn(BookingDtoMockData.mockBookingDtoValid());
        when(bookingRepository.save(any(BookingEntity.class))).thenReturn(BookingEntityMockData.mockBookingEntity());
        when(roomBookingRepository.save(any(RoomBookingEntity.class))).thenReturn(RoomBookingEntityMockData.mockRoomBookingEntity());

        BookingDto result = bookingService.create(3L, BookingDtoMockData.mockBookingDtoValid());

        verify(conflitsReservationService, Mockito.times(1)).checkConflicts(any(RoomEntity.class), any(LocalDate.class), any(LocalDate.class));
        verify(bookingRepository, Mockito.times(1)).save(any(BookingEntity.class));
        verify(roomBookingRepository, Mockito.times(1)).save(any(RoomBookingEntity.class));
        verify(roomRepository, Mockito.times(1)).findById(any(Long.class));
        assertNotNull(result);
    }

    @Test
    void givenIdBooking_whenDeleteBooking_thenReturnVoid(){
        when(roomBookingRepository.findByBookingEntityId(any(Long.class))).thenReturn(RoomBookingEntityMockData.mockRoomBookingEntity());

        bookingService.delete(3L);

        verify(roomBookingRepository, Mockito.times(1)).delete(any(RoomBookingEntity.class));
        verify(bookingRepository, Mockito.times(1)).deleteById(any(Long.class));

    }
    @Test
    void givenIdBooking_whenDeleteBooking_thenThrowBookingNotFoundException(){
        Throwable exception = assertThrows(BookingNotFoundException.class, () -> {
            when(roomBookingRepository.findByBookingEntityId(any(Long.class))).thenReturn(null);

            bookingService.delete(3L);
        });
        assertEquals(MSG_BOOKING_NOT_FOUND, exception.getMessage());
    }

    @Test
    void givenBookingIdAndBookingInfo_whenEditBooking_thenReturnBookingDto(){
        when(dateReservationValidation.validateDate(any(BookingDto.class))).thenReturn(Boolean.TRUE);
        when(bookingMapper.toBookingDto(any(RoomBookingEntity.class))).thenReturn(BookingDtoMockData.mockBookingDtoValid());
        when(roomBookingRepository.save(roomBookingCaptor.capture())).thenReturn(RoomBookingEntityMockData.mockRoomBookingEntity());
        when(roomBookingRepository.findByBookingEntityId(any(Long.class))).thenReturn(RoomBookingEntityMockData.mockRoomBookingEntity());

        BookingDto result = bookingService.editBooking(3L, BookingDtoMockData.mockBookingDtoValid());

        verify(conflitsReservationService, Mockito.times(1)).checkConflicts(any(RoomEntity.class), any(LocalDate.class), any(LocalDate.class));
        verify(roomBookingRepository, Mockito.times(1)).save(any(RoomBookingEntity.class));
        assertNotNull(result);
        assertNotNull(roomBookingCaptor.getValue().getStartDate());
        assertNotNull(roomBookingCaptor.getValue().getEndDate());
    }

}