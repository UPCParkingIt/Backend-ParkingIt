package com.parkingit.recognition.interfaces.rest.resources;

import java.util.UUID;

public record CreateFRResource(
        UUID userId,
        UUID deviceId,
        byte[] faceImageData,
        String faceEncodingVector
) {
}
