package com.hotelbooking.hotelreservationsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RoomDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @JsonProperty(required = true)
    private String roomNumber;
    @JsonProperty(required = true)
    private int capacity;
    @JsonProperty(required = true)
    private double pricePerNight;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private HotelDTO hotel;
}
