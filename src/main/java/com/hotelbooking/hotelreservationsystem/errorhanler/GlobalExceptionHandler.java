package com.hotelbooking.hotelreservationsystem.errorhanler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleConflict(NoSuchElementException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BookingValidationException.class)
    public ResponseEntity<String> handleConflict(BookingValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
