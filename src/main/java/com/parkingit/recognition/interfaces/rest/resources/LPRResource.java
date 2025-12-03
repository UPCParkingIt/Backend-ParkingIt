package com.parkingit.recognition.interfaces.rest.resources;

import java.util.Date;
import java.util.UUID;

public record LPRResource(
        UUID id,
        UUID userId,
        UUID deviceId,
        UUID vehicleId,
        byte[] plateImageData,
        Date createdAt
) {
}
