package com.alten.hotel.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HOTEL")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelEntity {

    @Id
    private Long id;

    private String name;
}
