package com.parkingit.devices.interfaces.rest.resources;

import java.util.UUID;

public record CreateDeviceResource(
    String deviceName,
    UUID userId
) {
}
