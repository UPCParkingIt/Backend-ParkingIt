package com.parkingit.devices.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public enum DeviceType {
    FACE_RECOGNITION,
    LICENSE_PLATE_RECOGNITION
}
