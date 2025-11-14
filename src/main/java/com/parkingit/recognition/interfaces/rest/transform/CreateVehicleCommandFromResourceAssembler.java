package com.parkingit.recognition.interfaces.rest.transform;

import com.parkingit.recognition.domain.model.commands.CreateVehicleCommand;
import com.parkingit.recognition.interfaces.rest.resources.CreateVehicleResource;

public class CreateVehicleCommandFromResourceAssembler {
    public static CreateVehicleCommand toCommandFromResource(CreateVehicleResource resource) {
        return new CreateVehicleCommand(
                resource.licensePlateNumber(),
                resource.vehicleType(),
                resource.color()
        );
    }
}
