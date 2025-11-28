package com.hotelbooking.hotelreservationsystem.dao;

import com.hotelbooking.hotelreservationsystem.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}