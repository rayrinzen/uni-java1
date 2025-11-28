package com.hotelbooking.hotelreservationsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @JsonProperty(required = true)
    private String name;
    @JsonProperty(required = true)
    private String address;
}
