package com.parkingit.accesslogs.domain.model.queries;

import com.parkingit.accesslogs.domain.model.valueobjects.AccessType;

public record GetAllAccessLogsByAccessTypeQuery(AccessType accessType) {
}
