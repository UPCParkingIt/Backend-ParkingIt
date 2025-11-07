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
            var user = externalIamService.fetchUserById(command.userId());

            if (user == null) throw new IllegalArgumentException("User not found with ID: " + command.userId());

            var existingDevice = repository.existsDeviceByUser_IdAndDeviceNameNot(command.userId(), command.deviceName());

            if (existingDevice != null) {
                throw new IllegalArgumentException("Device with name '" + command.deviceName() + "' already exists for user ID: " + command.userId());
            }

            var newDevice = new Device(
                    command.deviceName(),
                    user
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
            var currentUserId = device.get().getUser() != null ? device.get().getUser().getId() : null;

            boolean shouldUpdateName = command.deviceName() != null && !command.deviceName().isBlank() && !command.deviceName().equals(currentName);
            boolean shouldUpdateUser = command.userId() != null && (!command.userId().equals(currentUserId));

            if (!shouldUpdateName && !shouldUpdateUser) {
                throw new IllegalArgumentException("No changes detected for device ID: " + command.deviceId());
            }

            var targetUserId = shouldUpdateUser ? command.userId() : currentUserId;
            var targetDeviceName = shouldUpdateName ? command.deviceName() : currentName;

            // Check uniqueness only if target values are present
            if (targetUserId != null && targetDeviceName != null) {
                var existingDevice = repository.existsDeviceByUser_IdAndDeviceNameNot(targetUserId, targetDeviceName);
                // repository method expected to indicate existence; adapt condition if it returns non-boolean
                if (Boolean.TRUE.equals(existingDevice) || (existingDevice != null && existingDevice.equals(Boolean.TRUE))) {
                    throw new IllegalArgumentException("Device with name '" + targetDeviceName + "' already exists for user ID: " + targetUserId);
                }
            }

            if (shouldUpdateUser) {
                var user = externalIamService.fetchUserById(command.userId());
                device.get().setUser(user);
            }

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
