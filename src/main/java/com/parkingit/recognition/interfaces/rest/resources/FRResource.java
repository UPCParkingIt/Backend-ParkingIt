package com.parkingit.recognition.interfaces.rest.resources;

import java.util.Date;
import java.util.UUID;

public record FRResource(
        UUID id,
        UUID userId,
        UUID deviceId,
        byte[] faceImageData,
        String faceImageFormat,
        Date createdAt
) {
}
