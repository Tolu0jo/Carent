package com.example.carent.service.impl;

import com.example.carent.dto.request.BookingRequestDto;
import com.example.carent.dto.request.PaymentRequest;

import com.example.carent.dto.response.BookingDto;
import com.example.carent.exception.BadRequestException;
import com.example.carent.exception.NotFoundException;
import com.example.carent.model.Booking;
import com.example.carent.model.Car;
import com.example.carent.model.User;
import com.example.carent.repository.BookingRepository;
import com.example.carent.repository.CarRepository;

import com.example.carent.service.BookingService;
import com.example.carent.service.FlutterService;
import com.example.carent.utils.SecurityUtils;
import com.example.carent.utils.TransactionReferenceGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor

public class BookingServiceImpl implements BookingService {
    private  final CarRepository carRepository;
    private final BookingRepository bookingRepository;
    private final FlutterService flutterService;

    @Override
    public Object createBooking(String id, BookingRequestDto bookingRequestDto) throws JsonProcessingException {
        User user = SecurityUtils.getCurrentUserDetails();
        Optional<Car>  car = carRepository.findById(Long.valueOf(id));
        if (car.isEmpty()) throw  new NotFoundException("Car with " +id + " not found.");
        if(car.get().isBooked()) throw new BadRequestException("Car with " +id + " is booked.");
        double amount = car.get().getPricePerHour()* bookingRequestDto.getBookingHours();
        Booking booking = Booking.builder()
                .bookingHours(bookingRequestDto.getBookingHours())
                .amount(amount)
                .car(car.get())
                .user(user)
                .startTime(LocalDateTime.now())
                .estimatedEndTime(LocalDateTime.now().plusHours(bookingRequestDto.getBookingHours()))
                .endTime(null)
                .build();
        car.get().setBooked(true);
        carRepository.save(car.get());
        Booking newBooking =bookingRepository.save(booking);
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .amount(amount)
                .meta(newBooking.getId())
                .tx_ref(TransactionReferenceGenerator.generateReference()).
                build();

        return flutterService.createPayment(paymentRequest);
    }

    @Override
    public Object endBooking(String id) throws JsonProcessingException {
          Booking booking = bookingRepository.findById(Long.valueOf(id)).get();
          LocalDateTime startTime = booking.getStartTime();
          LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);
        long hoursDifference = duration.toHours();

        if(hoursDifference < 1){
            booking.setEndTime(endTime);
            booking.getCar().setBooked(false);
            bookingRepository.save(booking);
            return "Booking ended Successfully";
        }
        booking.setEndTime(endTime);
        booking.getCar().setBooked(false);
        bookingRepository.save(booking);
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .amount(hoursDifference * booking.getCar().getPricePerHour())
                .meta(booking.getId())
                .tx_ref(TransactionReferenceGenerator.generateReference()).
                build();

      return flutterService.createPayment(paymentRequest);
    }

    @Override
    public List<BookingDto> getMyBookings() {
        User user= SecurityUtils.getCurrentUserDetails();
        List<Booking> bookings =bookingRepository.findBookingsByUser(user);
      return  bookings.stream().map(booking -> BookingDto.builder()
              .id(booking.getId())
              .carId(booking.getCar().getId())
                .amount(booking.getAmount())
                .active(booking.getCar().isBooked())
                .bookingHours(booking.getBookingHours())
                .startTime(booking.getStartTime())
                .estimatedEndTime(booking.getEstimatedEndTime())
                .endTime(booking.getEndTime())
                .build()).toList();
    }

    @Override
    public List<BookingDto> activeBookings(){
        User user= SecurityUtils.getCurrentUserDetails();
       List<Booking> bookingList=bookingRepository.findBookingsByUser(user);
      List<Booking> bookings =bookingList.stream().filter(booking -> (booking.getCar().isBooked())).toList();
        return  bookings.stream().map(booking -> BookingDto.builder()
                .id(booking.getId())
                .carId(booking.getCar().getId())
                .active(booking.getCar().isBooked())
                .amount(booking.getAmount())
                .bookingHours(booking.getBookingHours())
                .startTime(booking.getStartTime())
                .estimatedEndTime(booking.getEstimatedEndTime())
                .endTime(booking.getEndTime())
                .build()).toList();
    }

    @Override
    public List<BookingDto> inactiveBookings() {
        User user= SecurityUtils.getCurrentUserDetails();
        List<Booking> bookingList=bookingRepository.findBookingsByUser(user);
        List<Booking> bookings= bookingList.stream().filter(booking -> (!booking.getCar().isBooked())).toList();
        return  bookings.stream().map(booking -> BookingDto.builder()
                .id(booking.getId())
                .carId(booking.getCar().getId())
                .amount(booking.getAmount())
                .bookingHours(booking.getBookingHours())
                .active(booking.getCar().isBooked())
                .startTime(booking.getStartTime())
                .estimatedEndTime(booking.getEstimatedEndTime())
                .endTime(booking.getEndTime())
                .build()).toList();
    }

    @Override
    public List<BookingDto> allActiveBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        List<Booking> bookingList=  bookings.stream().filter(booking -> (booking.getCar().isBooked())).toList();
        return bookingList.stream().map(booking -> BookingDto.builder()
                .id(booking.getId())
                .carId(booking.getCar().getId())
                .active(booking.getCar().isBooked())
                .userId(booking.getUser().getId())
                .amount(booking.getAmount())
                .bookingHours(booking.getBookingHours())
                .startTime(booking.getStartTime())
                .estimatedEndTime(booking.getEstimatedEndTime())
                .endTime(booking.getEndTime())
                .build()).toList();
    }
}
