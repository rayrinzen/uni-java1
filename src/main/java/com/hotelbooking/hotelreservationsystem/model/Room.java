package com.hotelbooking.hotelreservationsystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;
    private int capacity; 
    private double pricePerNight;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hotel hotel;
}