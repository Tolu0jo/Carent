package com.example.carent.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity(name = "cars")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name="image")
    private String images;

    @Column(name = "year")
    private int year;

    @Column(name = "hourly_rate")
    private double price;

    @Column(name = "is_booked")
    private boolean isBooked;


}
