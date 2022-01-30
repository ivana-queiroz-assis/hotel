package com.alten.hotel.model.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class RoomResponse implements Serializable {
    private Boolean isAvailable;
}
