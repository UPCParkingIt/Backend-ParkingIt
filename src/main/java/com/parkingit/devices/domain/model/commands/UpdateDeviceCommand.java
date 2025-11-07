package com.parkingit.devices.domain.model.commands;

import java.util.UUID;

public record UpdateDeviceCommand(UUID deviceId, String deviceName, UUID userId) {
}
