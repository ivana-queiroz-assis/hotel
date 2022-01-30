package com.alten.hotel.exception;

public class ConflictBookingException extends RuntimeException{

    public ConflictBookingException(String message) {
        super(message);
    }
}
