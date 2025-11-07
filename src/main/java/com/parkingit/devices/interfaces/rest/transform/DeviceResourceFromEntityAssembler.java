package com.parkingit.devices.interfaces.rest.transform;

import com.parkingit.devices.domain.model.aggregates.Device;
import com.parkingit.devices.interfaces.rest.resources.DeviceResource;

public class DeviceResourceFromEntityAssembler {
    public static DeviceResource toResourceFromEntity(Device entity) {
        return new DeviceResource(
                entity.getId(),
                entity.getDeviceName(),
                entity.getUser().getId()
        );
    }
}
