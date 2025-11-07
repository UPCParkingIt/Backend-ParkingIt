package com.parkingit.devices.domain.model.aggregates;

import com.parkingit.iam.domain.model.aggregates.User;
import com.parkingit.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Device extends AuditableAbstractAggregateRoot<Device> {
    @NotNull
    public String deviceName;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    public Boolean isActive;

    public Device(String deviceName, User user) {
        this.deviceName = deviceName;
        this.user = user;
        this.isActive = true;
    }

    public Device() {}

    public void deactivate() {
        this.isActive = false;
    }

    public void activate() {
        this.isActive = true;
    }
}
