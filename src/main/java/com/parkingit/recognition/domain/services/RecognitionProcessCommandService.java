package com.parkingit.recognition.domain.services;

import com.parkingit.recognition.domain.model.commands.ActivateRecognitionProcessCommand;
import com.parkingit.recognition.domain.model.commands.DeactivateRecognitionProcessCommand;

import java.util.Optional;

public interface RecognitionProcessCommandService {
    Optional<Boolean> handle(ActivateRecognitionProcessCommand command);
    Optional<Boolean> handle(DeactivateRecognitionProcessCommand command);
}
