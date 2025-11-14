package com.parkingit.devices.interfaces.rest.resources;

import com.parkingit.devices.domain.model.valueobjects.DeviceType;

import java.util.UUID;

public record CreateDeviceResource(
    String deviceName,
    DeviceType deviceType
) {
}
