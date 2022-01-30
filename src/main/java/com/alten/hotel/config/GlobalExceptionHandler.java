package com.alten.hotel.config;

import com.alten.hotel.exception.ConflictBookingException;
import com.alten.hotel.exception.DateBookingException;
import com.alten.hotel.model.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DateBookingException.class, ConflictBookingException.class})
    public ResponseEntity<Object> handleExceptions(DateBookingException exception) {
        GenericResponse response = new GenericResponse();
        response.setMessage(exception.getMessage());
        response.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        response.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));

        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        return entity;
    }
}
