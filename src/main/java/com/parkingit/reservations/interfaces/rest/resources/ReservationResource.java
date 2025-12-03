package com.parkingit.reservations.interfaces.rest.resources;

import java.util.Date;
import java.util.UUID;

public record ReservationResource(
        UUID id,
        Date reservationDate,
        UUID userId,
        String location,
        String accessCode,
        Date expirationDate,
        Integer hours
) {
}
