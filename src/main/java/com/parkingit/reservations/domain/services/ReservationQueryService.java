package com.parkingit.reservations.domain.services;

import com.parkingit.reservations.domain.model.aggregates.Reservation;
import com.parkingit.reservations.domain.model.queries.GetAllReservationsByUserIdQuery;
import com.parkingit.reservations.domain.model.queries.GetAllReservationsQuery;
import com.parkingit.reservations.domain.model.queries.GetReservationByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReservationQueryService {
    Optional<Reservation> handle(GetReservationByIdQuery query);
    List<Reservation> handle(GetAllReservationsQuery query);
    List<Reservation> handle(GetAllReservationsByUserIdQuery query);
}
