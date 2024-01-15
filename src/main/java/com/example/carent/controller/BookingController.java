package com.example.carent.controller;

import com.example.carent.dto.request.BookingRequestDto;
import com.example.carent.dto.response.BookingDto;
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
    public ResponseEntity<List<BookingDto>> getActiveBooking(){
        List<BookingDto> bookings = bookingService.activeBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/history")
    public ResponseEntity<List<BookingDto>> bookingHistory(){
        List<BookingDto> bookings = bookingService.inactiveBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<BookingDto>> allMyBookings(){
        List<BookingDto> bookings = bookingService.getMyBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/all_active_bookings")
    public ResponseEntity< List<BookingDto> > allActiveBookings(){
        List<BookingDto> bookings = bookingService.allActiveBookings();
        return ResponseEntity.ok(bookings);
    }
}
