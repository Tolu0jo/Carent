package com.example.carent.service;

import com.example.carent.dto.request.BookingRequestDto;
import com.example.carent.dto.response.BookingDto;
import com.example.carent.model.Booking;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface BookingService {

Object createBooking (String id, BookingRequestDto bookingRequestDto) throws JsonProcessingException;
Object endBooking(String id) throws JsonProcessingException;

List<Booking> getMyBookings();
List<Booking> activeBookings();
List<Booking> inactiveBookings();

List<Booking> allActiveBookings();

}
