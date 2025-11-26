package com.parkingit.devices.domain.model.aggregates;

import com.parkingit.devices.domain.model.valueobjects.DeviceType;
import com.parkingit.iam.domain.model.aggregates.User;
import com.parkingit.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Device extends AuditableAbstractAggregateRoot<Device> {
    @NotNull
    private String deviceName;

    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    private Boolean isActive;

    public Device() {}

    public Device(String deviceName, DeviceType deviceType) {
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void activate() {
        this.isActive = true;
    }
}
