package com.parkingit.recognition.interfaces.rest.resources;

import com.parkingit.recognition.domain.model.valueobjects.VehicleType;

public record CreateVehicleResource(
        String licensePlateNumber,
        VehicleType vehicleType,
        String color
) {
}
