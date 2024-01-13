package com.example.carent.controller;

import com.example.carent.dto.request.BookingRequestDto;
import com.example.carent.service.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public String endBooking(@PathVariable String id) throws JsonProcessingException {
        bookingService.endBooking(id);
        return "Booking ended, thanks for your patronage";
    }
}
