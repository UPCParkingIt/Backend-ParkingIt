package com.parkingit.recognition.domain.model.commands;

import com.parkingit.recognition.domain.model.valueobjects.VehicleType;

public record CreateVehicleCommand(String licensePlateNumber, VehicleType vehicleType, String color) {
}
