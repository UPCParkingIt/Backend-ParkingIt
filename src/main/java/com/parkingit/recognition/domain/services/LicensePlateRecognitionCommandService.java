package com.parkingit.recognition.domain.services;

import com.parkingit.recognition.domain.model.aggregates.LicensePlateRecognition;
import com.parkingit.recognition.domain.model.commands.ActivateLicensePlateRecognitionCommand;
import com.parkingit.recognition.domain.model.commands.CreateLicensePlateRecognitionCommand;
import com.parkingit.recognition.domain.model.commands.DeactivateLicensePlateRecognitionCommand;

import java.util.Optional;

public interface LicensePlateRecognitionCommandService {
    Optional<LicensePlateRecognition> handle(CreateLicensePlateRecognitionCommand query);
    void handle(DeactivateLicensePlateRecognitionCommand command);
    void handle(ActivateLicensePlateRecognitionCommand command);
}
