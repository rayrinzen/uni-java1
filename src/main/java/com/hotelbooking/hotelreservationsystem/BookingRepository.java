package com.hotelbooking.hotelreservationsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByRoomHotelId(Long hotelId);
}