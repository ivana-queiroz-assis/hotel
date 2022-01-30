package com.alten.hotel.data;

import com.alten.hotel.model.entity.BookingEntity;
import com.alten.hotel.model.entity.RoomBookingEntity;
import com.alten.hotel.model.entity.RoomEntity;

public class RoomBookingEntityMockData {

    public static RoomBookingEntity mockRoomBookingEntity(){
        return RoomBookingEntity.builder().id(1L).roomEntity(RoomEntity.builder().id(10L).build()).build();
    }
}
