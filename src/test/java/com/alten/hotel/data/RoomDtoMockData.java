package com.alten.hotel.data;

import com.alten.hotel.model.dto.RoomDto;

public class RoomDtoMockData {

    public static RoomDto mockRoomDtoAvailable(){
        return RoomDto.builder().isAvailable(Boolean.TRUE).build();
    }

}
