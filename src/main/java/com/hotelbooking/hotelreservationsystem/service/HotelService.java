package com.hotelbooking.hotelreservationsystem.service;

import com.hotelbooking.hotelreservationsystem.dao.BookingRepository;
import com.hotelbooking.hotelreservationsystem.dao.HotelRepository;
import com.hotelbooking.hotelreservationsystem.dao.RoomRepository;
import com.hotelbooking.hotelreservationsystem.errorhanler.BookingValidationException;
import com.hotelbooking.hotelreservationsystem.model.Booking;
import com.hotelbooking.hotelreservationsystem.model.Hotel;
import com.hotelbooking.hotelreservationsystem.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BookingRepository bookingRepository;

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel getHotel(Long id) {
        return hotelRepository.findById(id).orElseThrow();
    }

    public Room createRoom(Long hotelId, Room room) {
        Hotel hotel = getHotel(hotelId);
        room.setHotel(hotel);
        return roomRepository.save(room);
    }

    public Room getRoom(Long id) {
        return roomRepository.findById(id).orElseThrow();
    }

    public Booking createBooking(Long roomId, Booking booking) {
        Room room = getRoom(roomId);
        
        if (!booking.getCheckOutDate().isAfter(booking.getCheckInDate())) {
            throw new BookingValidationException("Check-out date should be after check-in date");
        }

        if (booking.getCheckInDate().isBefore(LocalDate.now())) {
            throw new BookingValidationException("Check-in date should be in future");
        }

        if (!isRoomAvailable(room, booking)) {
            throw new BookingValidationException("Booking for specified dates is not available");
        }

        booking.setRoom(room);
        return bookingRepository.save(booking);
    }

    private boolean isRoomAvailable(Room room, Booking booking) {
        List<Booking> bookings = bookingRepository.findByRoomIdAndDates(room.getId(), booking.getCheckOutDate(), booking.getCheckInDate());
        return bookings.isEmpty();
    }

    public List<Booking> getAllBookingsForHotel(Long hotelId) {
        return bookingRepository.findByRoomHotelId(hotelId);
    }
}
