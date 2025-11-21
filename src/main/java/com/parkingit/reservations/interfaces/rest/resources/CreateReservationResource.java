package com.parkingit.reservations.interfaces.rest.resources;

import java.util.Date;
import java.util.UUID;

public record CreateReservationResource(
        Date reservationDate,
        UUID userId,
        String location
) {
}
