package com.example.carent.service;

import com.example.carent.dto.request.CarDto;
import com.example.carent.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    Car addCar (CarDto carDto);

    List<Car> getAllCar(Optional<String> model,Optional<String> brand);

    Car getCar(String id);

    void deleteCar(String id);

    Car editCar(CarDto carDto, String id);

    List<Car>getBookedCars();

    List<Car> getUnbookedCars();
}
