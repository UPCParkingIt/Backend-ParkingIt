package com.parkingit.reservations.application.internal.commandservices;

import com.parkingit.devices.application.internal.outboundservices.acl.ExternalIamService;
import com.parkingit.notifications.application.internal.outboundservices.acl.ExternalEmailService;
import com.parkingit.notifications.application.internal.outboundservices.acl.ExternalNotificationService;
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
    private final ExternalNotificationService externalNotificationService;
    private final ExternalEmailService externalEmailService;

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
                    command.location(),
                    command.hours()
            );

            var savedReservation = reservationRepository.save(newReservation);

            externalNotificationService.createNotification(
                    userResult.getId(),
                    "Reservación Creada",
                    "Tu reservación en " + command.location() + " el día " + command.reservationDate() + " ha sido creada."
            );

            externalEmailService.sendEmail(
                    userResult.getEmail(),
                    "Confirmación de Reservación ParkingIt",
                    "Hola " + userResult.getFirstName() + ",\n\nTu reservación en " + command.location() + " el día " + command.reservationDate() + " ha sido creada exitosamente.\n\nTu código de reservación es: " + newReservation.getAccessCode() + "\n\nGracias por usar ParkingIt!",
                    ""
            );

            return Optional.of(savedReservation);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create reservation: " + e.getMessage(), e);
        }
    }
}
