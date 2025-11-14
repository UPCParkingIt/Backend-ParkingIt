package com.parkingit.devices.domain.model.commands;

import com.parkingit.devices.domain.model.valueobjects.DeviceType;

import java.util.UUID;

public record UpdateDeviceCommand(UUID deviceId, String deviceName, DeviceType deviceType) {
}
