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

    @GetMapping("history")
    public ResponseEntity<List<Booking>> bookingHistory(){
        List<Booking> bookings = bookingService.inactiveBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/allBookings")
    public ResponseEntity<List<Booking>> allBookings(){
        List<Booking> bookings = bookingService.getMyBookings();
        return ResponseEntity.ok(bookings);
    }
}
