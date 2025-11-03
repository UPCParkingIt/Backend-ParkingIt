package com.parkingit.iam.domain.model.queries;

import com.parkingit.iam.domain.model.valueobjects.Roles;

public record GetAllUsersByRoleQuery(Roles role) {
}
