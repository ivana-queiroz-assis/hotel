package com.alten.hotel.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "BOOK")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingEntity {

    @Id
    @Generated
    private Long id;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

}
