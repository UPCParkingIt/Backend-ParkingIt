package com.parkingit.devices.domain.services;

import com.parkingit.devices.domain.model.aggregates.Device;
import com.parkingit.devices.domain.model.queries.GetAllDevicesByNameQuery;
import com.parkingit.devices.domain.model.queries.GetAllDevicesQuery;
import com.parkingit.devices.domain.model.queries.GetDeviceByIdQuery;

import java.util.List;
import java.util.Optional;

public interface DeviceQueryService {
    Optional<Device> handle(GetDeviceByIdQuery query);
    List<Device> handle(GetAllDevicesQuery query);
    List<Device> handle(GetAllDevicesByNameQuery query);
}
