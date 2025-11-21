package com.parkingit.reservations.application.internal.commandservices;

import com.parkingit.devices.application.internal.outboundservices.acl.ExternalIamService;
import com.parkingit.reservations.domain.model.aggregates.Reservation;
import com.parkingit.reservations.domain.model.commands.CreateReservationCommand;
import com.parkingit.reservations.domain.services.ReservationCommandService;
import com.parkingit.reservations.infrastructure.persistence.jpa.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationCommandServiceImpl implements ReservationCommandService {
    private final ReservationRepository reservationRepository;
    private final ExternalIamService externalIamService;

    @Override
    public Optional<Reservation> handle(CreateReservationCommand command) {
        try {
            var userResult = externalIamService.fetchUserById(command.userId());

            if (userResult == null) {
                throw new IllegalArgumentException("User not found with ID: " + command.userId());
            }

            var newReservation = new Reservation(
                    command.reservationDate(),
                    userResult,
                    command.location()
            );

            var savedReservation = reservationRepository.save(newReservation);
            return Optional.of(savedReservation);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create reservation: " + e.getMessage(), e);
        }
    }
}
