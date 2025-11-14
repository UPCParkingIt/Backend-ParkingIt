package com.parkingit.accesslogs.domain.model.queries;

import com.parkingit.accesslogs.domain.model.valueobjects.AccessResult;

public record GetAllAccessLogsByAccessResultQuery(AccessResult accessResult) {
}
