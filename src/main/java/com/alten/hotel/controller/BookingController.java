package com.alten.hotel.controller;

import com.alten.hotel.model.dto.BookingDto;
import com.alten.hotel.model.generic.GenericResponse;
import com.alten.hotel.service.BookingService;
import com.alten.hotel.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @DeleteMapping("/{id}")
    public GenericResponse<Void> deleteBooking(@PathVariable("id") Long id) {
        this.bookingService.delete(id);
        return new GenericResponse<>(AppConstants.SUCCESS, String.valueOf(HttpStatus.NO_CONTENT), AppConstants.OK);
    }

    @PutMapping("/{id}")
    public GenericResponse<BookingDto> putBooking(@PathVariable("id") Long id,  @RequestBody final BookingDto bookingDto) {
        BookingDto result =this.bookingService.editBooking(id, bookingDto);
        return new GenericResponse<>(AppConstants.SUCCESS, String.valueOf(HttpStatus.OK), AppConstants.OK, result);
    }

}
