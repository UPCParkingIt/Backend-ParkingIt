package com.parkingit.accesslogs.interfaces.rest.resources;

import com.parkingit.accesslogs.domain.model.valueobjects.AccessType;
import com.parkingit.accesslogs.domain.model.valueobjects.AuthenticationMethod;

import java.util.Date;
import java.util.UUID;

public record AccessLogResource(
        UUID id,
        UUID userId,
        UUID deviceId,
        AccessType accessType,
        AuthenticationMethod authenticationMethod,
        Date createdAt
) {
}
