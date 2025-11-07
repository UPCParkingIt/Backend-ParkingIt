package com.parkingit.devices.application.internal.queryservices;

import com.parkingit.devices.domain.model.aggregates.Device;
import com.parkingit.devices.domain.model.queries.GetAllDevicesByNameQuery;
import com.parkingit.devices.domain.model.queries.GetAllDevicesByUserIdQuery;
import com.parkingit.devices.domain.model.queries.GetAllDevicesQuery;
import com.parkingit.devices.domain.model.queries.GetDeviceByIdQuery;
import com.parkingit.devices.domain.services.DeviceQueryService;
import com.parkingit.devices.infrastructure.persistence.jpa.repositories.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeviceQueryServiceImpl implements DeviceQueryService {
    private final DeviceRepository repository;

    @Override
    public Optional<Device> handle(GetDeviceByIdQuery query) {
        return repository.findById(query.deviceId());
    }

    @Override
    public List<Device> handle(GetAllDevicesQuery query) {
        return repository.findAll();
    }

    @Override
    public List<Device> handle(GetAllDevicesByUserIdQuery query) {
        return repository.findAllByUser_Id(query.userId());
    }

    @Override
    public List<Device> handle(GetAllDevicesByNameQuery query) {
        return repository.findDevicesByDeviceNameContains(query.deviceName());
    }
}
