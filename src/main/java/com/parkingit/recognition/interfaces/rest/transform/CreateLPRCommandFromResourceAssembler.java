package com.parkingit.recognition.interfaces.rest.transform;

import com.parkingit.recognition.domain.model.commands.CreateLicensePlateRecognitionCommand;
import com.parkingit.recognition.interfaces.rest.resources.CreateLPRResource;

public class CreateLPRCommandFromResourceAssembler {
    public static CreateLicensePlateRecognitionCommand toCommandFromResource(CreateLPRResource resource){
        return new CreateLicensePlateRecognitionCommand(
                resource.userId(),
                resource.deviceId(),
                resource.vehicleId(),
                resource.plateImageData()
        );
    }
}
