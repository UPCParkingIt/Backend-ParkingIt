package com.parkingit.devices.domain.model.commands;

import java.util.UUID;

public record CreateDeviceCommand(String deviceName, UUID userId) {
}
