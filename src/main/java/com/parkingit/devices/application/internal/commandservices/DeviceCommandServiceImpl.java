package com.parkingit.devices.application.internal.commandservices;

import com.parkingit.devices.application.internal.outboundservices.acl.ExternalIamService;
import com.parkingit.devices.domain.model.aggregates.Device;
import com.parkingit.devices.domain.model.commands.ActivateDeviceCommand;
import com.parkingit.devices.domain.model.commands.CreateDeviceCommand;
import com.parkingit.devices.domain.model.commands.DeactivateDeviceCommand;
import com.parkingit.devices.domain.model.commands.UpdateDeviceCommand;
import com.parkingit.devices.domain.services.DeviceCommandService;
import com.parkingit.devices.infrastructure.persistence.jpa.repositories.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeviceCommandServiceImpl implements DeviceCommandService {
    private final DeviceRepository repository;
    private final ExternalIamService externalIamService;

    @Override
    public Optional<Device> handle(CreateDeviceCommand command) {
        try {
            var newDevice = new Device(
                    command.deviceName(),
                    command.deviceType()
            );

            var savedDevice = repository.save(newDevice);
            return Optional.of(savedDevice);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create device: " + e.getMessage(), e);
        }
    }

    @Override
    public void handle(DeactivateDeviceCommand command) {
        try {
            var device = repository.findById(command.deviceId());

            if (device.isEmpty()) {
                throw new IllegalArgumentException("Device not found with ID: " + command.deviceId());
            }

            device.get().deactivate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to deactivate device: " + e.getMessage(), e);
        }
    }

    @Override
    public void handle(ActivateDeviceCommand command) {
        try {
            var device = repository.findById(command.deviceId());

            if (device.isEmpty()) {
                throw new IllegalArgumentException("Device not found with ID: " + command.deviceId());
            }

            device.get().activate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to deactivate device: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Device> handle(UpdateDeviceCommand command) {
        try {
            var device = repository.findById(command.deviceId());

            if (device.isEmpty()) {
                throw new IllegalArgumentException("Device not found with ID: " + command.deviceId());
            }

            var currentName = device.get().getDeviceName();

            boolean shouldUpdateName = command.deviceName() != null && !command.deviceName().isBlank() && !command.deviceName().equals(currentName);

            if (!shouldUpdateName) {
                throw new IllegalArgumentException("No changes detected for device ID: " + command.deviceId());
            }

            var targetDeviceName = shouldUpdateName ? command.deviceName() : currentName;

            if (shouldUpdateName) {
                device.get().setDeviceName(command.deviceName());
            }

            var savedDevice = repository.save(device.get());
            return Optional.of(savedDevice);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deactivate device: " + e.getMessage(), e);
        }
    }
}
