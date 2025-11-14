package com.parkingit.recognition.domain.model.commands;

import java.util.UUID;

public record CreateFaceRecognitionCommand(UUID userId, UUID deviceId, byte[] faceImageData, String faceEncodingVector) {
}
