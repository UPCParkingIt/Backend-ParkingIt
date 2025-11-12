package com.parkingit.recognition.domain.model.commands;

import java.util.UUID;

public record CreateLicensePlateRecognitionCommand(UUID userId, UUID deviceId, UUID vehicleId, byte[] plateImageData) {
}
