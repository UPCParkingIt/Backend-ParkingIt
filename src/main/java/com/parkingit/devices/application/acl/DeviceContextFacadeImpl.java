package com.parkingit.devices.application.acl;

import com.parkingit.devices.domain.model.aggregates.Device;
import com.parkingit.devices.domain.model.queries.GetDeviceByIdQuery;
import com.parkingit.devices.domain.services.DeviceQueryService;
import com.parkingit.devices.interfaces.acl.DeviceContextFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DeviceContextFacadeImpl implements DeviceContextFacade {
    private final DeviceQueryService deviceQueryService;

    @Override
    public Optional<Device> fetchDeviceById(UUID deviceId) {
        return deviceQueryService.handle(new GetDeviceByIdQuery(deviceId));
    }
}
