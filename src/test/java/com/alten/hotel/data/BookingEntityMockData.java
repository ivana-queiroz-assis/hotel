package com.alten.hotel.data;

import com.alten.hotel.model.entity.BookingEntity;
import com.alten.hotel.model.entity.RoomEntity;

public class BookingEntityMockData {

    public static BookingEntity mockBookingEntity(){
        return BookingEntity.builder().id(1L).build();
    }
}
