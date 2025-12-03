package com.parkingit.recognition.interfaces.rest.transform;

import com.parkingit.recognition.domain.model.aggregates.LicensePlateRecognition;
import com.parkingit.recognition.interfaces.rest.resources.LPRResource;

public class LPRResourceFromEntityAssembler {
    public static LPRResource toResourceFromEntity(LicensePlateRecognition entity) {
        return new LPRResource(
                entity.getId(),
                entity.getUser().getId(),
                entity.getDevice().getId(),
                entity.getVehicle().getId(),
                entity.getPlateImageData(),
                entity.getCreatedAt()
        );
    }
}
