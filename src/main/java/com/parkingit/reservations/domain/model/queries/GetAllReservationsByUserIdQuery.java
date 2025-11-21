package com.parkingit.reservations.domain.model.queries;

import java.util.UUID;

public record GetAllReservationsByUserIdQuery(UUID userId) {
}
