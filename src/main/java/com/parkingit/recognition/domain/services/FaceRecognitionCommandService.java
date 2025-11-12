package com.parkingit.recognition.domain.services;

import com.parkingit.recognition.domain.model.aggregates.FaceRecognition;
import com.parkingit.recognition.domain.model.commands.ActivateFaceRecognitionCommand;
import com.parkingit.recognition.domain.model.commands.CreateFaceRecognitionCommand;
import com.parkingit.recognition.domain.model.commands.DeactivateFaceRecognitionCommand;

import java.util.Optional;

public interface FaceRecognitionCommandService {
    Optional<FaceRecognition> handle(CreateFaceRecognitionCommand command);
    void handle(DeactivateFaceRecognitionCommand command);
    void handle(ActivateFaceRecognitionCommand command);
}
