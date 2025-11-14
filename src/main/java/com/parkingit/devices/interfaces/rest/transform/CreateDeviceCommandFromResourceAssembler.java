package com.parkingit.devices.interfaces.rest.transform;

import com.parkingit.devices.domain.model.commands.CreateDeviceCommand;
import com.parkingit.devices.interfaces.rest.resources.CreateDeviceResource;

public class CreateDeviceCommandFromResourceAssembler {
    public static CreateDeviceCommand toCommandFromResource(CreateDeviceResource resource) {
        return new CreateDeviceCommand(
                resource.deviceName(),
                resource.deviceType()
        );
    }
}
