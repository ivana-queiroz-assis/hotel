package com.alten.hotel.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingService {

    private RoomRepository roomRepository;
    private RoomBookingRepository roomBookingRepository;
    private DateReservationValidationOfDateService dateReservationValidation;
    private ConflitsReservationService conflitsReservationService;
    private BookingRepository bookingRepository;
    private BookingMapper bookingMapper;

    @Autowired
    public BookingService(RoomRepository roomRepository, RoomBookingRepository roomBookingRepository, DateReservationValidationOfDateService dateReservationValidation, ConflitsReservationService conflitsReservationService, BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.roomRepository = roomRepository;
        this.roomBookingRepository = roomBookingRepository;
        this.dateReservationValidation = dateReservationValidation;
        this.conflitsReservationService = conflitsReservationService;
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    public BookingDto create(Long id, BookingDto bookingDto){
        dateReservationValidation.validateDate(bookingDto);
        return bookingMapper.toBookingDto(placeReservation(id, bookingDto));
    }

    public void delete(Long id){
        RoomBookingEntity roomBookingEntity = roomBookingRepository.findByBookingEntityId(id);
        roomBookingRepository.delete(roomBookingEntity);
        bookingRepository.deleteById(id);
    }

    public BookingDto editBooking(Long id, BookingDto bookingDto){
        dateReservationValidation.validateDate(bookingDto);
        return bookingMapper.toBookingDto(editReservation(id, bookingDto));
    }

    private RoomBookingEntity editReservation(Long id, BookingDto bookingDto){
        RoomBookingEntity roomBookingEntity = roomBookingRepository.findByBookingEntityId(id);
        conflitsReservationService.checkConflicts(roomBookingEntity.getRoomEntity(), bookingDto.getStartDate(), bookingDto.getEndDate());
        return updateRoomBooking(bookingDto, roomBookingEntity);
    }

    private RoomBookingEntity updateRoomBooking(BookingDto bookingDto, RoomBookingEntity roomBookingEntity) {
        roomBookingEntity.setStartDate(bookingDto.getStartDate());
        roomBookingEntity.setEndDate(bookingDto.getEndDate());
        return roomBookingRepository.save(roomBookingEntity);
    }

    private RoomBookingEntity placeReservation(Long idRoom, BookingDto bookingDto){
        RoomEntity roomEntity = roomRepository.findById(idRoom).get();
        conflitsReservationService.checkConflicts(roomEntity, bookingDto.getStartDate(), bookingDto.getEndDate());
        return saveBooking(bookingDto, roomEntity);
    }

    private RoomBookingEntity saveBooking(BookingDto bookingDto, RoomEntity roomEntity) {
        BookingEntity bookingEntity = BookingEntity.builder().createDate(LocalDateTime.now()).build();
        bookingEntity = bookingRepository.save(bookingEntity);

        RoomBookingEntity roomBookingEntity = RoomBookingEntity.builder().roomEntity(roomEntity)
                .bookingEntity(bookingEntity).startDate(bookingDto.getStartDate()).endDate(bookingDto.getEndDate()).build();

        return roomBookingRepository.save(roomBookingEntity);
    }

}
