package com.alten.hotel.service.validation.conflits;

import com.alten.hotel.exception.ConflictBookingException;
import com.alten.hotel.model.entity.RoomEntity;
import com.alten.hotel.repository.RoomBookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.alten.hotel.util.AppConstants.MSG_BOOKING_ROOM_ALREADY_RESERVED;

@Service
public class ConflitsReservationService {

    private RoomBookingRepository roomBookedRepository;

    public ConflitsReservationService(RoomBookingRepository roomBookedRepository) {
        this.roomBookedRepository = roomBookedRepository;
    }

    public void checkConflicts(RoomEntity roomEntity, LocalDate startDate, LocalDate endDate){
        Integer numberOfConflicts =  roomBookedRepository.countRoomBookedEntityByRoomEntityIdAndStartDateIsBeforeAndEndDateIsAfter(roomEntity.getId(), endDate, startDate);
        if (numberOfConflicts> 0){
            throw new ConflictBookingException(MSG_BOOKING_ROOM_ALREADY_RESERVED);
        }
    }
}
