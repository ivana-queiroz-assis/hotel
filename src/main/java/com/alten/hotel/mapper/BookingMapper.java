package com.alten.hotel.mapper;

import com.alten.hotel.model.dto.BookingDto;
import com.alten.hotel.model.entity.RoomBookingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( componentModel = "spring")
public interface BookingMapper {

    @Mapping(source = "roomEntity.id", target = "roomId")
    @Mapping(source = "bookingEntity.id", target = "id")
    BookingDto toBookingDto(RoomBookingEntity roomBookingEntity);



}
