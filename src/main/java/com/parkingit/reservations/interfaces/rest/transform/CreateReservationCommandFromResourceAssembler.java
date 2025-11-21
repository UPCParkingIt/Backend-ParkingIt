package com.parkingit.reservations.interfaces.rest.transform;

import com.parkingit.reservations.domain.model.commands.CreateReservationCommand;
import com.parkingit.reservations.interfaces.rest.resources.CreateReservationResource;

public class CreateReservationCommandFromResourceAssembler {
    public static CreateReservationCommand toCommandFromResource(CreateReservationResource resource) {
        return new CreateReservationCommand(
                resource.reservationDate(),
                resource.userId(),
                resource.location()
        );
    }
}
