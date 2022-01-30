package com.alten.hotel.service;

import com.alten.hotel.model.dto.RoomDto;
import com.alten.hotel.repository.RoomBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RoomService {

    private RoomBookingRepository roomBookedRepository;

    @Autowired
    public RoomService(RoomBookingRepository roomBookedRepository) {
        this.roomBookedRepository = roomBookedRepository;
    }

    public RoomDto checkAvailability(Long id, LocalDate startDate, LocalDate endDate){
        Boolean isAvailable =roomBookedRepository.countRoomBookedEntityByRoomEntityIdAndStartDateIsBeforeAndEndDateIsAfter(id, endDate, startDate) > 0 ? false: true;
        return RoomDto.builder().isAvailable(isAvailable).build();
    }
}
