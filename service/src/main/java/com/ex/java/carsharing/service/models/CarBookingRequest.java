package com.ex.java.carsharing.service.models;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class CarBookingRequest {
    @NotBlank
    private String carId;
    @NotBlank
    private String customerId;
}
