package com.parkingit.devices.domain.services;

import com.parkingit.devices.domain.model.aggregates.Device;
import com.parkingit.devices.domain.model.commands.ActivateDeviceCommand;
import com.parkingit.devices.domain.model.commands.CreateDeviceCommand;
import com.parkingit.devices.domain.model.commands.DeactivateDeviceCommand;
import com.parkingit.devices.domain.model.commands.UpdateDeviceCommand;

import java.util.Optional;

public interface DeviceCommandService {
    Optional<Device> handle(CreateDeviceCommand command);
    void handle(DeactivateDeviceCommand command);
    void handle(ActivateDeviceCommand command);
    Optional<Device> handle(UpdateDeviceCommand command);
}
