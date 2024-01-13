package com.example.carent.service;

import com.example.carent.dto.request.BookingRequestDto;
import com.example.carent.dto.response.BookingDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface BookingService {

Object createBooking (String id, BookingRequestDto bookingRequestDto) throws JsonProcessingException;
Object endBooking(String id) throws JsonProcessingException;
}
