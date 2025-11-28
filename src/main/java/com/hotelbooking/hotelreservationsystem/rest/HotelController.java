package com.hotelbooking.hotelreservationsystem.rest;

import com.hotelbooking.hotelreservationsystem.dto.BookingDTO;
import com.hotelbooking.hotelreservationsystem.dto.HotelDTO;
import com.hotelbooking.hotelreservationsystem.dto.RoomDTO;
import com.hotelbooking.hotelreservationsystem.model.Booking;
import com.hotelbooking.hotelreservationsystem.model.Hotel;
import com.hotelbooking.hotelreservationsystem.model.Room;
import com.hotelbooking.hotelreservationsystem.service.HotelService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<HotelDTO> createHotel(@RequestBody Hotel hotelDTO) {
        Hotel createdHotel = hotelService.createHotel(modelMapper.map(hotelDTO, Hotel.class));
        return ResponseEntity.ok(modelMapper.map(createdHotel, HotelDTO.class));
    }

    @PostMapping("/{hotelId}/rooms")
    public ResponseEntity<RoomDTO> addRoomToHotel(@PathVariable Long hotelId, @RequestBody RoomDTO roomDTO) {
        Room createdRoom = hotelService.createRoom(hotelId, modelMapper.map(roomDTO, Room.class));
        return ResponseEntity.ok(modelMapper.map(createdRoom, RoomDTO.class));
    }

    @PostMapping("/rooms/{roomId}/book")
    public ResponseEntity<BookingDTO> createBooking(@PathVariable Long roomId, @RequestBody BookingDTO bookingRequest) {

        Booking booking = modelMapper.map(bookingRequest, Booking.class);
        Booking savedBooking = hotelService.createBooking(roomId, booking);
        return ResponseEntity.ok(modelMapper.map(savedBooking, BookingDTO.class));
    }

    @GetMapping("/{hotelId}/bookings")
    public List<BookingDTO> getAllBookingsForHotel(@PathVariable Long hotelId) {
        List<Booking> bookings = hotelService.getAllBookingsForHotel(hotelId);
        return modelMapper.map(bookings, new TypeToken<List<BookingDTO>>() {
        }.getType());
    }
}