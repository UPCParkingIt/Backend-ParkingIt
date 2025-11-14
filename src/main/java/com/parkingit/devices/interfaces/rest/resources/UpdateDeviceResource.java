package com.parkingit.devices.interfaces.rest.resources;

import com.parkingit.devices.domain.model.valueobjects.DeviceType;

public record UpdateDeviceResource(
        String deviceName,
        DeviceType deviceType
) {
}
