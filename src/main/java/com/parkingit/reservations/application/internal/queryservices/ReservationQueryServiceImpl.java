package com.parkingit.reservations.application.internal.queryservices;

import com.parkingit.reservations.domain.model.aggregates.Reservation;
import com.parkingit.reservations.domain.model.queries.GetAllReservationsByUserIdQuery;
import com.parkingit.reservations.domain.model.queries.GetAllReservationsQuery;
import com.parkingit.reservations.domain.model.queries.GetReservationByIdQuery;
import com.parkingit.reservations.domain.services.ReservationQueryService;
import com.parkingit.reservations.infrastructure.persistence.jpa.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationQueryServiceImpl implements ReservationQueryService {
    private final ReservationRepository reservationRepository;

    @Override
    public Optional<Reservation> handle(GetReservationByIdQuery query) {
        return reservationRepository.findById(query.reservationId());
    }

    @Override
    public List<Reservation> handle(GetAllReservationsQuery query) {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> handle(GetAllReservationsByUserIdQuery query) {
        return reservationRepository.findAllByUserId(query.userId());
    }
}
