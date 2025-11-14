package com.parkingit.accesslogs.interfaces.rest.resources;

import com.parkingit.accesslogs.domain.model.valueobjects.AccessType;
import com.parkingit.accesslogs.domain.model.valueobjects.AuthenticationMethod;

import java.util.UUID;

public record CreateAccessLogResource(
        UUID userId,
        UUID deviceId,
        AccessType accessType,
        AuthenticationMethod authenticationMethod
) {
}
