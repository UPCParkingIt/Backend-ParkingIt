package com.parkingit.accesslogs.domain.model.queries;

import java.util.UUID;

public record GetAllAccessLogsByUserIdQuery(UUID userId) {
}
