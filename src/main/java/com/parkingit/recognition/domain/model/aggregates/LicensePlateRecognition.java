package com.parkingit.recognition.domain.model.aggregates;

import com.parkingit.devices.domain.model.aggregates.Device;
import com.parkingit.iam.domain.model.aggregates.User;
import com.parkingit.recognition.domain.model.entities.Vehicle;
import com.parkingit.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LicensePlateRecognition extends AuditableAbstractAggregateRoot<LicensePlateRecognition> {
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private Device device;

    @OneToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;

    @NotNull
    @Lob
    private byte[] plateImageData;

<<<<<<< Updated upstream
=======
    private Boolean isActive;

>>>>>>> Stashed changes
    public LicensePlateRecognition() {}

    public LicensePlateRecognition(User user, Device device, Vehicle vehicle, byte[] plateImageData) {
        this.user = user;
        this.device = device;
        this.vehicle = vehicle;
        this.plateImageData = plateImageData;
<<<<<<< Updated upstream
=======
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void activate() {
        this.isActive = true;
>>>>>>> Stashed changes
    }
}
