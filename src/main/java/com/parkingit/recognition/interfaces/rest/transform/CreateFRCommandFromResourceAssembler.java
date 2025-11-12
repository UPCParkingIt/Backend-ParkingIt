package com.parkingit.recognition.interfaces.rest.transform;

import com.parkingit.recognition.domain.model.commands.CreateFaceRecognitionCommand;
import com.parkingit.recognition.interfaces.rest.resources.CreateFRResource;

public class CreateFRCommandFromResourceAssembler {
    public static CreateFaceRecognitionCommand toCommandFromResource(CreateFRResource resource){
        return new CreateFaceRecognitionCommand(
                resource.userId(),
                resource.deviceId(),
                resource.faceImageData(),
                resource.faceEncodingVector()
        );
    }
}
