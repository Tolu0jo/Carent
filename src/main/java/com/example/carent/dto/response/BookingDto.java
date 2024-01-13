package com.example.carent.dto.response;

import com.example.carent.model.Car;
import com.example.carent.model.User;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter
public class BookingDto {

    private User user;

    private Car car;

    private int bookingHours;

    private double amount;

    private LocalDateTime startTime;

    private LocalDateTime estimatedEndTime;

    private String paymentLink;

    private String TransactionId;
}
