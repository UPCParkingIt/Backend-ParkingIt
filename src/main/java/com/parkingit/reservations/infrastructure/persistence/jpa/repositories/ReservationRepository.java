package com.parkingit.reservations.infrastructure.persistence.jpa.repositories;

import com.parkingit.reservations.domain.model.aggregates.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    List<Reservation> findAllByUserId(UUID userId);
    List<Reservation> findByIsActiveTrueAndExpirationDateBefore(Date currentDate);
}
