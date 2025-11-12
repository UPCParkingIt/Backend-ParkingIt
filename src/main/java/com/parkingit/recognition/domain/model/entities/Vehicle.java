package com.parkingit.recognition.domain.model.entities;

import com.parkingit.iam.domain.model.aggregates.User;
import com.parkingit.recognition.domain.model.valueobjects.VehicleStatus;
import com.parkingit.recognition.domain.model.valueobjects.VehicleType;
import com.parkingit.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vehicle extends AuditableModel {
    @NotNull
    @Pattern(regexp = "^[A-Z0-9]{6,8}$")
    @Column(unique = true)
    private String licensePlateNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VehicleStatus vehicleStatus;

    @NotNull
    private String color;

    private boolean isActive;

    public Vehicle() {}

    public Vehicle(String licensePlateNumber, VehicleType vehicleType, String color) {
        this.licensePlateNumber = licensePlateNumber;
        this.vehicleType = vehicleType;
        this.color = color;
        this.isActive = true;
        this.vehicleStatus = VehicleStatus.UNREGISTERED;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void activate() {
        this.isActive = true;
    }

    public void registerVehicle() {
        this.vehicleStatus = VehicleStatus.REGISTERED;
    }

    public void blockVehicle() {
        this.vehicleStatus = VehicleStatus.BLOCKED;
    }
}
