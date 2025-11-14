package com.parkingit.accesslogs.domain.model.queries;

import java.util.UUID;

public record GetAllAccessLogsByDeviceIdQuery(UUID deviceId) {
}
