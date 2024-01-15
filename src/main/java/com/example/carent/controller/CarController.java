package com.example.carent.controller;

import com.example.carent.dto.request.CarDto;
import com.example.carent.model.Car;
import com.example.carent.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/car")
@RequiredArgsConstructor
public class CarController {
    private  final CarService carService;
    @PostMapping("/add_car")
    public ResponseEntity<Car> addCar (@RequestBody CarDto carDto){
        Car car=carService.addCar(carDto);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity< List<Car> > getAllCar (@RequestParam Optional<String> model, @RequestParam Optional<String> brand){
       List<Car> cars=carService.getAllCar(model,brand);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PatchMapping("edit/{id}")
    public ResponseEntity<Car> editCar ( @RequestBody  CarDto carDto, @PathVariable String id){
        Car car=carService.editCar(carDto,id);
        return ResponseEntity.ok(car);
    }
    @GetMapping("/single_car/{id}")
    public ResponseEntity<Car> getCar (@PathVariable String id){
        Car car=carService.getCar(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCar (@PathVariable String id){
        carService.deleteCar(id);
        return new ResponseEntity<>("Car " + id  + " deleted Successfully",HttpStatus.GONE);
    }

    @GetMapping("/booked_cars")
    public ResponseEntity<List<Car>> getBookedCar(){
       List<Car> cars = carService.getBookedCars();
        return ResponseEntity.ok(cars);
    }
    @GetMapping("/unbooked_cars")
    public ResponseEntity<List<Car>> getUnbookedCar(){
        List<Car> cars = carService.getUnbookedCars();
        return ResponseEntity.ok(cars);
    }
}
