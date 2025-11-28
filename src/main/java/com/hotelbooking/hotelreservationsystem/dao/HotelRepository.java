package com.hotelbooking.hotelreservationsystem.dao;

import com.hotelbooking.hotelreservationsystem.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}