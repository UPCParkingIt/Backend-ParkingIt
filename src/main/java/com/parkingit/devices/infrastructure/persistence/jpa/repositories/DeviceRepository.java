package com.parkingit.devices.infrastructure.persistence.jpa.repositories;

import com.parkingit.devices.domain.model.aggregates.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {
    List<Device> findAllByUser_Id(UUID userId);
    List<Device> findDevicesByDeviceNameContains(String deviceName);
    Object existsDeviceByUser_IdAndDeviceNameNot(UUID userId, String deviceName);
}
