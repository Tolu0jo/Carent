package com.example.carent.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "bookings")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "booking_hours")
    private int bookingHours;

    @Column(name = "amount")
    private double amount;
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "estimated_end_time")
    private LocalDateTime estimatedEndTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;


}
