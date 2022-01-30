package com.alten.hotel.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ROOM")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomEntity {

    @Id
    private Long id;

    private String number;

    @ManyToOne
    @JoinColumn(name = "ID_HOTEL", nullable = false)
    private HotelEntity hotelEntity;
}
