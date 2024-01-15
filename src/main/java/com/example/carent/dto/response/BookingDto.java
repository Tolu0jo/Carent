package com.example.carent.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookingDto {
   private Long id;
    private Long userId;
    private Long carId;
    private boolean active;
    private int bookingHours;

    private double amount;

    private LocalDateTime startTime;

    private LocalDateTime estimatedEndTime;
    private LocalDateTime endTime;

}
