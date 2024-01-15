package com.example.carent.repository;

import com.example.carent.model.Booking;
import com.example.carent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
   List< Booking > findBookingsByUser(User user);
}
