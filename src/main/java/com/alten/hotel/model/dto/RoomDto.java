package com.alten.hotel.model.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class RoomDto implements Serializable {
    private Boolean isAvailable;
}
