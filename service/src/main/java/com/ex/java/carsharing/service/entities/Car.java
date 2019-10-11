package com.ex.java.carsharing.service.entities;

import lombok.Data;

import java.util.List;

@Data
public class Car {
    private String modelName;
    private String engine;
    private String manufacturer;
    private List<String> colors;
    private String transmission;
    private Integer seats;
}
