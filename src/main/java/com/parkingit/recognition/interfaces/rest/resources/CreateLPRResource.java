package com.parkingit.recognition.interfaces.rest.resources;

import java.util.UUID;

public record CreateLPRResource(
        UUID userId,
        UUID deviceId,
        UUID vehicleId,
        byte[] plateImageData
) {
}
