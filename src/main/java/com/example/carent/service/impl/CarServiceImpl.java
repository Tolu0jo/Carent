package com.example.carent.service.impl;

import com.example.carent.dto.request.CarDto;
import com.example.carent.exception.NotFoundException;
import com.example.carent.model.Car;
import com.example.carent.repository.CarRepository;
import com.example.carent.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {

    private final  CarRepository carRepository;
    @Override
    public Car addCar(CarDto carDto) {

        Car car = Car.builder()
                .brand(carDto.getBrand())
                .images(carDto.getImages())
                .pricePerHour(carDto.getPricePerHour())
                .year(carDto.getYear())
                .model(carDto.getModel())
                .isBooked(false)
                .build();
        return carRepository.save(car);
    }

    @Override
    public List<Car> getAllCar(Optional<String> model,Optional<String> brand) {

        if(model.isPresent() && brand.isPresent())
            return carRepository.findCarsByBrandAndModel(model.get(),brand.get());
        if(model.isPresent())
            return carRepository.findCarsByModel(model.get());
        if(brand.isPresent())
            return carRepository.findCarsByBrand(brand.get());
        return carRepository.findAll();
    }

    @Override
    public Car getCar(String id) {
        Optional<Car> car = carRepository.findById(Long.valueOf(id));
        if(car.isEmpty()) throw new NotFoundException("Car not found");
        return car.get();
    }
    @Override
    public void deleteCar(String id) {
        Optional<Car> car = carRepository.findById(Long.valueOf(id));
        if(car.isEmpty()) throw new NotFoundException("Car not found");

         carRepository.delete(car.get());
    }

    @Override
    public Car editCar(CarDto carDto,String id) {
        Optional<Car> existingCar = carRepository.findById(Long.valueOf(id));
        if(existingCar.isEmpty()) throw new NotFoundException("Car not found");
        BeanUtils.copyProperties(carDto, existingCar.get(), getNullPropertyNames(carDto));
        return carRepository.save(existingCar.get());
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        return emptyNames.toArray(new String[0]);
    }

}
