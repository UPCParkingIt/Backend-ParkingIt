package com.parkingit.recognition.application.internal.commandservices;

import com.parkingit.recognition.domain.model.commands.CreateVehicleCommand;
import com.parkingit.recognition.domain.model.entities.Vehicle;
import com.parkingit.recognition.domain.services.VehicleCommandService;
import com.parkingit.recognition.infrastructure.persistence.jpa.repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VehicleCommandServiceImpl implements VehicleCommandService {
    private final VehicleRepository vehicleRepository;

    @Override
    public Optional<Vehicle> handle(CreateVehicleCommand command) {
        var newVehicle = new Vehicle(
                command.licensePlateNumber(),
                command.vehicleType(),
                command.color()
        );

        var savedVehicle = vehicleRepository.save(newVehicle);
        return Optional.of(savedVehicle);
    }
}
