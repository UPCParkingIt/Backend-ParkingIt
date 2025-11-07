package com.parkingit.devices.interfaces.rest.transform;

import com.parkingit.devices.domain.model.commands.UpdateDeviceCommand;
import com.parkingit.devices.interfaces.rest.resources.UpdateDeviceResource;

import java.util.UUID;

public class UpdateDeviceCommandFromResourceAssembler {
    public static UpdateDeviceCommand toCommandFromResource(UUID id, UpdateDeviceResource resource) {
        return new UpdateDeviceCommand(
                id,
                resource.deviceName(),
                resource.userId()
        );
    }
}
