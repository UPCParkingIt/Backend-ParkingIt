package com.parkingit.recognition.interfaces.rest.transform;

import com.parkingit.recognition.domain.model.aggregates.FaceRecognition;
import com.parkingit.recognition.domain.model.aggregates.LicensePlateRecognition;
import com.parkingit.recognition.interfaces.rest.resources.FRResource;

public class FRResourceFromEntityAssembler {
    public static FRResource toResourceFromEntity(FaceRecognition entity) {
        return new FRResource(
                entity.getId(),
                entity.getUser().getId(),
                entity.getDevice().getId(),
                entity.getFaceImageData(),
                entity.getFaceEncodingVector(),
                entity.getCreatedAt()
        );
    }
}
