package com.example.carent.controller;

import com.example.carent.dto.request.CarDto;
import com.example.carent.model.Car;
import com.example.carent.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/car")
@RequiredArgsConstructor
public class CarController {
    private  final CarService carService;
    @PostMapping
    public ResponseEntity<Car> addCar (@RequestBody CarDto carDto){
        Car car=carService.addCar(carDto);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity< List<Car> > getAllCar (){
       List<Car> cars=carService.getAllCar();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Car> editCar ( @RequestBody  CarDto carDto, @PathVariable String id){
        Car car=carService.editCar(carDto,id);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<Car> getCar (@PathVariable String id){
        Car car=carService.getCar(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCar (@PathVariable String id){
        carService.deleteCar(id);
        return new ResponseEntity<>("Car " + id  + " deleted Successfully",HttpStatus.GONE);
    }

}
