package com.parkingit.accesslogs.domain.model.queries;

import com.parkingit.accesslogs.domain.model.valueobjects.AuthenticationMethod;

public record GetAllAccessLogsByAuthenticationMethodQuery(AuthenticationMethod authenticationMethod) {
}
