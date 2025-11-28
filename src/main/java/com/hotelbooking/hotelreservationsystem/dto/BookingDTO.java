package com.hotelbooking.hotelreservationsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @JsonProperty(required = true)
    private LocalDate checkInDate;
    @JsonProperty(required = true)
    private LocalDate checkOutDate;
    @JsonProperty(required = true)
    private String guestName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private RoomDTO room;
}
