package com.parkingit.recognition.interfaces.rest.transform;

import com.parkingit.recognition.domain.model.entities.Vehicle;
import com.parkingit.recognition.interfaces.rest.resources.VehicleResource;

public class VehicleResourceFromEntityAssembler {
    public static VehicleResource toResourceFromEntity(Vehicle entity) {
        return new VehicleResource(
                entity.getId(),
                entity.getLicensePlateNumber(),
                entity.getVehicleType(),
                entity.getColor(),
                entity.getCreatedAt()
        );
    }
}
