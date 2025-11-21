package com.parkingit.reservations.domain.services;

import com.parkingit.reservations.domain.model.aggregates.Reservation;
import com.parkingit.reservations.domain.model.commands.CreateReservationCommand;

import java.util.Optional;
import java.util.UUID;

public interface ReservationCommandService {
    Optional<Reservation> handle(CreateReservationCommand command);
}
