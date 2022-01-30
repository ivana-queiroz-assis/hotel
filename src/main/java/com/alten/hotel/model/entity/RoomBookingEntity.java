package com.alten.hotel.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ROOM_BOOKING")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookingEntity {

    @Id
    @Generated
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_ROOM", nullable = false)
    private RoomEntity roomEntity;

    @ManyToOne
    @JoinColumn(name = "ID_BOOKING", nullable = false)
    private BookingEntity bookingEntity;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "START_DATE")
    private LocalDate startDate;
}

