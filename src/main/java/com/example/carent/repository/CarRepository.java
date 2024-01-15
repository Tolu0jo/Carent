package com.example.carent.repository;

import com.example.carent.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findCarsByBrandAndModel(String brand,String model);
    List<Car> findCarsByBrand(String brand);
    List<Car> findCarsByModel(String model);

}
