package com.hotelbooking.hotelreservationsystem.dao;

import com.hotelbooking.hotelreservationsystem.model.Booking;
import org.hibernate.annotations.processing.HQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByRoomHotelId(Long hotelId);

    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId AND b.checkInDate < :checkOutDate AND b.checkOutDate > :checkInDate")
    List<Booking> findByRoomIdAndDates(Long roomId, LocalDate checkOutDate, LocalDate checkInDate);
}