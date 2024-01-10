package com.example.carent.dto.request;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class CarDto {

    private String brand;


    private String model;

    private List<String> images;

    private int year;

    private double pricePerHour;

    private boolean isBooked;
}
