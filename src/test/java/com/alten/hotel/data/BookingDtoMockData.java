package com.alten.hotel.data;

import com.alten.hotel.model.dto.BookingDto;

import java.time.LocalDate;

public class BookingDtoMockData {

    public static BookingDto mockBookingDtoValid(){
        return BookingDto.builder().startDate(LocalDate.now().plusDays(1)).endDate(LocalDate.now().plusDays(3)).build();
    }

    public static BookingDto mockBookingDtoLimitMaxDaysExceed(){
        return BookingDto.builder().startDate(LocalDate.now().plusDays(1)).endDate(LocalDate.now().plusDays(35)).build();
    }
    public static BookingDto mockBookingDtoMaxLimit30DaysInAdvance(){
        return BookingDto.builder().startDate(LocalDate.now().plusDays(1)).endDate(LocalDate.now().plusDays(31)).build();
    }
    public static BookingDto mockBookingDtoMaxStayInHotel(){
        return BookingDto.builder().startDate(LocalDate.now().plusDays(1)).endDate(LocalDate.now().plusDays(5)).build();
    }

    public static BookingDto mockBookingDto3DaysOfStayInHotel(){
        return BookingDto.builder().startDate(LocalDate.now().plusDays(1)).endDate(LocalDate.now().plusDays(4)).build();
    }
    public static BookingDto mockBookingDtoEndDateBiggerThanStartDate(){
        return BookingDto.builder().startDate(LocalDate.now().plusDays(10)).endDate(LocalDate.now().plusDays(5)).build();
    }

    public static BookingDto mockBookingDtoWithoutOneDayOfAdvance(){
        return BookingDto.builder().startDate(LocalDate.now()).endDate(LocalDate.now().plusDays(3)).build();
    }
}
