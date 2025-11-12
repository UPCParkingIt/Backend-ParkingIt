package com.parkingit.recognition.application.internal.outboundservices.acl;

import com.parkingit.devices.domain.model.aggregates.Device;
import com.parkingit.devices.interfaces.acl.DeviceContextFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ExternalDeviceService {
    private final DeviceContextFacade deviceContextFacade;

    public Device fetchDeviceById(UUID deviceId) {
        return deviceContextFacade.fetchDeviceById(deviceId)
                .orElseThrow(() -> new NoSuchElementException("Device not found: " + deviceId));
    }
}
