package com.parkingit.devices.interfaces.rest.resources;

import com.parkingit.devices.domain.model.valueobjects.DeviceType;

import java.util.UUID;

public record DeviceResource(
        UUID id,
        String deviceName,
        DeviceType deviceType
) {
}
