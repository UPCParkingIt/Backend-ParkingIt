package com.parkingit.devices.interfaces.rest.resources;

import java.util.UUID;

public record DeviceResource(
        UUID id,
        String deviceName,
        UUID userId
) {
}
