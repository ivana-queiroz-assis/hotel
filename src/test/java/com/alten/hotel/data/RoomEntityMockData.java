package com.alten.hotel.data;

import com.alten.hotel.model.entity.RoomEntity;

public class RoomEntityMockData {

    public static RoomEntity mockBookingEntity(){
        return RoomEntity.builder().id(1L).build();
    }
}
