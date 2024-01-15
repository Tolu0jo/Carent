package com.example.carent.controller;

import com.example.carent.dto.request.BookingRequestDto;
import com.example.carent.model.Booking;
import com.example.carent.service.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    @PostMapping("{id}")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequestDto bookingRequestDto, @PathVariable String id) throws JsonProcessingException {
       Object object = bookingService.createBooking(id,bookingRequestDto);
       return ResponseEntity.ok(object);
    }

    @GetMapping("{id}")
    public ResponseEntity<?>  endBooking(@PathVariable String id) throws JsonProcessingException {
        Object object = bookingService.endBooking(id);
        return ResponseEntity.ok(object);
    }

    @GetMapping("active_bookings")
    public ResponseEntity<List<Booking>> getActiveBooking(){
        List<Booking> bookings = bookingService.activeBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("my_booking_history")
    public ResponseEntity<List<Booking>> bookingHistory(){
        List<Booking> bookings = bookingService.inactiveBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/all_my_bookings")
    public ResponseEntity<List<Booking>> allMyBookings(){
        List<Booking> bookings = bookingService.getMyBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/all_active_bookings")
    public ResponseEntity<List<Booking>> allActiveBookings(){
        List<Booking> bookings = bookingService.allActiveBookings();
        return ResponseEntity.ok(bookings);
    }
}
