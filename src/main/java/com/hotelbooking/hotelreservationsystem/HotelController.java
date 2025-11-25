package com.hotelbooking.hotelreservationsystem;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;


    public HotelController(HotelRepository hotelRepository, RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }


    @PostMapping
    public Hotel createHotel(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }
    
   
    @PostMapping("/{hotelId}/rooms")
    public ResponseEntity<Room> addRoomToHotel(@PathVariable Long hotelId, @RequestBody Room newRoom) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);

        if (hotelOptional.isPresent()) {
            Hotel hotel = hotelOptional.get();
            newRoom.setHotel(hotel);
            Room savedRoom = roomRepository.save(newRoom);
            return ResponseEntity.ok(savedRoom);
        } else {
           
            return ResponseEntity.notFound().build(); 
        }
    }

    @PostMapping("/rooms/{roomId}/book")
    public ResponseEntity<Booking> createBooking(@PathVariable Long roomId, @RequestBody Booking bookingRequest) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);

        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            if (!room.isAvailable()) {
                 
                 return ResponseEntity.badRequest().build(); 
            }

          
            bookingRequest.setRoom(room); 
           
            room.setAvailable(false); 
            roomRepository.save(room);
            
            Booking savedBooking = bookingRepository.save(bookingRequest);
            return ResponseEntity.ok(savedBooking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{hotelId}/bookings")
    public List<Booking> getAllBookingsForHotel(@PathVariable Long hotelId) {
   
        return bookingRepository.findByRoomHotelId(hotelId); 
    }
}