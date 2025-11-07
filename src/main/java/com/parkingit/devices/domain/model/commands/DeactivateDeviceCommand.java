package com.parkingit.devices.domain.model.commands;

import java.util.UUID;

public record DeactivateDeviceCommand(UUID deviceId) {
}
