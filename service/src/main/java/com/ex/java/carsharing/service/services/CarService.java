package com.ex.java.carsharing.service.services;

import com.ex.java.carsharing.service.entities.Car;
import com.ex.java.carsharing.service.mappers.CarMapper;
import com.ex.java.carsharing.service.models.CarBookingRequest;
import com.ex.java.carsharing.service.models.CarModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Example how-to work with GC-firestore: https://cloud.spring.io/spring-cloud-static/spring-cloud-gcp/1.2.0.M1/#_sample_14
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CarService {

    private final Firestore firestore;
    private final CarMapper carMapper;

    public List<CarModel> findCars(String brand, Integer seatsCount) {
        ApiFuture<QuerySnapshot> carsFuture = firestore.collection("cars").get();
        try {
            return carsFuture.get().getDocuments().stream()
                    .map(document -> document.toObject(Car.class))
                    .map(carMapper::toCarModel)
                    .collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error occurred:", e);
            return List.of();
        }
    }

    public Optional<String> createCar(CarModel carModel) {
        log.info("Creating new car: {}", carModel);
        try {
            Car car = carMapper.toCarEntity(carModel);
            DocumentReference carDocument = this.firestore.collection("cars").document();
            WriteResult writeResult = carDocument.set(car).get();
            log.info("Update time: {}", writeResult.getUpdateTime());
            return Optional.of(carDocument.getId());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error occurred:", e);
            return Optional.empty();
        }
    }

    public void bookCar(CarBookingRequest carBookingRequest) {

    }
}
