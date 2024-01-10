package com.example.carent.service;

import com.example.carent.dto.request.CarDto;
import com.example.carent.model.Car;

import java.util.List;

public interface CarService {

    Car addCar (CarDto carDto);

    List<Car> getAllCar();

    Car getCar(String id);

    void deleteCar(String id);

    Car editCar(CarDto carDto, String id);
}
