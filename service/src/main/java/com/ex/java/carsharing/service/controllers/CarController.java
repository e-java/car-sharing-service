package com.ex.java.carsharing.service.controllers;

import com.ex.java.carsharing.service.models.Car;
import com.ex.java.carsharing.service.models.CarBookingRequest;
import com.ex.java.carsharing.service.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> findCars(
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "seats", required = false) Integer seatsCount
    ) {
        return ResponseEntity.ok(carService.findCars(brand, seatsCount));
    }

    @PutMapping
    public ResponseEntity bookCar(@Valid @RequestBody CarBookingRequest carBookingRequest) {
        carService.bookCar(carBookingRequest);
        return ResponseEntity.ok().build();
    }
}
