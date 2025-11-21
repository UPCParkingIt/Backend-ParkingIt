package com.parkingit.reservations.domain.model.commands;

import java.util.Date;
import java.util.UUID;

public record CreateReservationCommand(Date reservationDate, UUID userId, String location) {
}
