package com.parkingit.devices.interfaces.acl;

import com.parkingit.devices.domain.model.aggregates.Device;

import java.util.Optional;
import java.util.UUID;

public interface DeviceContextFacade {
    Optional<Device> fetchDeviceById(UUID deviceId);
}
