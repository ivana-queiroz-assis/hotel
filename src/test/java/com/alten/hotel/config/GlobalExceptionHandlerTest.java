package com.alten.hotel.config;

import com.alten.hotel.exception.DateBookingException;
import com.alten.hotel.model.generic.GenericResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;


    @Test
    void givenException_whenExecutingAllCalls_thenReturnResponseEntity(){
        String messageBookingException = "DateBookingException";
        ResponseEntity response = globalExceptionHandler.handleExceptions(new DateBookingException(messageBookingException));
        GenericResponse genericResponse = (GenericResponse.class.cast(response.getBody()));

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof GenericResponse);
        assertEquals(  String.valueOf(HttpStatus.BAD_REQUEST.value()), genericResponse.getCode() );
        assertEquals( String.valueOf(HttpStatus.BAD_REQUEST.value()), genericResponse.getStatus() );
        assertEquals( messageBookingException, genericResponse.getMessage() );
    }
}