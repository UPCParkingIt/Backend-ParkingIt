package com.parkingit.reservations.application.internal.outboundservices;

import com.parkingit.reservations.infrastructure.persistence.jpa.repositories.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Slf4j
public class ReservationSchedulerService {
    private final ReservationRepository reservationRepository;

    public ReservationSchedulerService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void deactivateExpiredReservations() {
        log.info("Verificando reservas expiradas...");

        var expiredReservations = reservationRepository
                .findByIsActiveTrueAndExpirationDateBefore(new Date());

        expiredReservations.forEach(reservation -> {
            reservation.setIsActive(false);
            log.info("Reserva {} desactivada por expiración", reservation.getId());
        });

        reservationRepository.saveAll(expiredReservations);

        log.info("Total de reservas desactivadas: {}", expiredReservations.size());
    }
}
