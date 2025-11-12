package com.parkingit.recognition.interfaces.rest.resources;

import com.parkingit.recognition.domain.model.valueobjects.VehicleType;

import java.util.UUID;

public record VehicleResource(
        UUID id,
        String licensePlateNumber,
        VehicleType vehicleType,
        String color
) {
}
