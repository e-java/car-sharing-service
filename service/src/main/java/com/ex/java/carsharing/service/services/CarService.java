package com.ex.java.carsharing.service.services;

import com.ex.java.carsharing.service.models.Car;
import com.ex.java.carsharing.service.models.CarBookingRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CarService {
    public List<Car> findCars(String brand, Integer seatsCount) {
        return List.of(
                new Car("Audi", 4)
        );
    }

    public void bookCar(CarBookingRequest carBookingRequest) {

    }
}
