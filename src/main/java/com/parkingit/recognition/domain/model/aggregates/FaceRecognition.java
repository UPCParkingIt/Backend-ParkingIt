package com.parkingit.recognition.domain.model.aggregates;

import com.parkingit.devices.domain.model.aggregates.Device;
import com.parkingit.iam.domain.model.aggregates.User;
import com.parkingit.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FaceRecognition extends AuditableAbstractAggregateRoot<FaceRecognition> {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private Device device;

    @NotNull
    @Lob
    private byte[] faceImageData;

    @NotNull
    @Column(length = 2048)
    private String faceEncodingVector;

    private Boolean isActive;

    public FaceRecognition() {}

    public FaceRecognition(User user, Device device, byte[] faceImageData, String faceEncodingVector) {
        this.user = user;
        this.device = device;
        this.faceImageData = faceImageData;
        this.faceEncodingVector = faceEncodingVector;
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void activate() {
        this.isActive = true;
    }
}
