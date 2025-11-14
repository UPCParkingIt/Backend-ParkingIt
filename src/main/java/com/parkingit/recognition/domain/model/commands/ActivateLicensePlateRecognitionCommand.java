package com.parkingit.recognition.domain.model.commands;

import java.util.UUID;

public record ActivateLicensePlateRecognitionCommand(UUID licensePlateRecognitionId) {
}
