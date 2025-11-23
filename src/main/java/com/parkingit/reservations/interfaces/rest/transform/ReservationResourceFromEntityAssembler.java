package com.parkingit.reservations.interfaces.rest.transform;

import com.parkingit.reservations.domain.model.aggregates.Reservation;
import com.parkingit.reservations.interfaces.rest.resources.ReservationResource;

public class ReservationResourceFromEntityAssembler {
    public static ReservationResource toResourceFromEntity(Reservation entity) {
        return new ReservationResource(
                entity.getId(),
                entity.getReservationDate(),
                entity.getUser().getId(),
                entity.getLocation(),
                entity.getHours()
        );
    }
}
