package com.parkingit.devices.interfaces.rest.resources;

import java.util.UUID;

public record UpdateDeviceResource(
        String deviceName,
        UUID userId
) {
}
