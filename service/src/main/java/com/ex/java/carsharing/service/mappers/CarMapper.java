package com.ex.java.carsharing.service.mappers;

import com.ex.java.carsharing.service.entities.Car;
import com.ex.java.carsharing.service.models.CarModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CarMapper {

    @Mapping(source = "modelName", target = "model")
    @Mapping(source = "seats", target = "seatsCount")
    CarModel toCarModel(Car car);

    @InheritInverseConfiguration
    Car toCarEntity(CarModel carModel);
}
