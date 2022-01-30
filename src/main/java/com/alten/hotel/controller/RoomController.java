package com.alten.hotel.controller;

import com.alten.hotel.model.dto.BookingDto;
import com.alten.hotel.model.dto.RoomDto;
import com.alten.hotel.model.generic.GenericResponse;
import com.alten.hotel.service.BookingService;
import com.alten.hotel.service.RoomService;
import com.alten.hotel.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/room")
public class RoomController {

    private RoomService roomService;
    private BookingService bookingService;

    @Autowired
    public RoomController(RoomService roomService, BookingService bookingService) {
        this.roomService = roomService;
        this.bookingService = bookingService;
    }

    @PostMapping("/{roomId}/booking")
    public GenericResponse<BookingDto> postBooking(@PathVariable("roomId") Long roomId , @RequestBody final BookingDto bookingDto) {
        BookingDto result = this.bookingService.create(roomId, bookingDto);
        return new GenericResponse<>(AppConstants.SUCCESS, String.valueOf(HttpStatus.CREATED), AppConstants.OK, result);
    }

    @GetMapping("/{id}")
    public GenericResponse<RoomDto> getRoom(@PathVariable("id") Long id, @RequestParam String startDate, @RequestParam String endDate) {
        RoomDto result = this.roomService.checkAvailability(id, LocalDate.parse(startDate), LocalDate.parse(endDate));
        return new GenericResponse<>(AppConstants.SUCCESS, String.valueOf(HttpStatus.OK), AppConstants.OK, result);
    }

}
