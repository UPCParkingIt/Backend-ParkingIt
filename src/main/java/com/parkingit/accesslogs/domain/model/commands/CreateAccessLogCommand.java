package com.parkingit.accesslogs.domain.model.commands;

import com.parkingit.accesslogs.domain.model.valueobjects.AccessType;
import com.parkingit.accesslogs.domain.model.valueobjects.AuthenticationMethod;

import java.util.UUID;

public record CreateAccessLogCommand(UUID userId, UUID deviceId, AccessType accessType, AuthenticationMethod authenticationMethod) {
}
