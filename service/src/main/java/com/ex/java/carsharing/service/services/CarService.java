package com.ex.java.carsharing.service.services;

import com.ex.java.carsharing.service.entities.Car;
import com.ex.java.carsharing.service.models.CarBookingRequest;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Car> findCars(String brand, Integer seatsCount) {
        ApiFuture<QuerySnapshot> carsFuture = firestore.collection("cars").get();
        try {
            return carsFuture.get().getDocuments().stream()
                    .map(document -> document.toObject(Car.class))
                    .collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error occurred:", e);
            return List.of();
        }
    }

    public boolean createCar(Car car) {
        try {
            WriteResult writeResult = this.firestore.collection("cars").document().set(car).get();
            log.info("Update time: {}", writeResult.getUpdateTime());
            return true;
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error occurred:", e);
            return false;
        }
    }

    public void bookCar(CarBookingRequest carBookingRequest) {

    }
}
