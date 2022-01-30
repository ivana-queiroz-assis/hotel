package com.alten.hotel.repository;

import com.alten.hotel.model.entity.RoomBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBookingEntity, Long> {

    Integer countRoomBookedEntityByRoomEntityIdAndStartDateIsBeforeAndEndDateIsAfter
            (Long idRoom,LocalDate endDate, LocalDate startDate);

    RoomBookingEntity findByBookingEntityId(Long id);
}