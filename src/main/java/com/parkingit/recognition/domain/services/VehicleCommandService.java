package com.parkingit.recognition.domain.services;

import com.parkingit.recognition.domain.model.commands.CreateVehicleCommand;
import com.parkingit.recognition.domain.model.entities.Vehicle;

import java.util.Optional;

public interface VehicleCommandService {
    Optional<Vehicle> handle(CreateVehicleCommand command);
}
