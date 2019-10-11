package com.ex.java.carsharing.service.models;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CarModel {
    private String manufacturer;
    private String model;
    private String engine;
    private List<String> colors;
    private String transmission;
    private Integer seatsCount;
}
