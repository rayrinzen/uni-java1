package com.hotelbooking.hotelreservationsystem.errorhanler;

public class BookingValidationException extends RuntimeException {

    public BookingValidationException(String message) {
        super(message);
    }
}
