package com.parkingit.reservations.interfaces.rest;

import com.parkingit.reservations.domain.model.queries.GetAllReservationsByUserIdQuery;
import com.parkingit.reservations.domain.model.queries.GetAllReservationsQuery;
import com.parkingit.reservations.domain.model.queries.GetReservationByIdQuery;
import com.parkingit.reservations.domain.services.ReservationCommandService;
import com.parkingit.reservations.domain.services.ReservationQueryService;
import com.parkingit.reservations.interfaces.rest.resources.CreateReservationResource;
import com.parkingit.reservations.interfaces.rest.resources.ReservationResource;
import com.parkingit.reservations.interfaces.rest.transform.CreateReservationCommandFromResourceAssembler;
import com.parkingit.reservations.interfaces.rest.transform.ReservationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reservations", description = "Reservations Management Endpoint")
public class ReservationsController {
    private final ReservationCommandService reservationCommandService;
    private final ReservationQueryService reservationQueryService;

    @PostMapping
    public ResponseEntity<ReservationResource> createReservation(@RequestBody CreateReservationResource resource) {
        var createReservationCommand = CreateReservationCommandFromResourceAssembler.toCommandFromResource(resource);
        var reservation = reservationCommandService.handle(createReservationCommand);
        if (reservation.isEmpty()) { return ResponseEntity.badRequest().build(); }
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(reservation.get());
        return new ResponseEntity<>(reservationResource, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResource> getReservationById(@PathVariable UUID id) {
        var getReservationByIdQuery = new GetReservationByIdQuery(id);
        var reservation = reservationQueryService.handle(getReservationByIdQuery);
        if (reservation.isEmpty()) { return ResponseEntity.notFound().build(); }
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(reservation.get());
        return ResponseEntity.ok(reservationResource);
    }

    @GetMapping
    public ResponseEntity<List<ReservationResource>> getAllReservations() {
        var reservations = reservationQueryService.handle(new GetAllReservationsQuery());
        var reservationResources = reservations.stream()
                .map(ReservationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(reservationResources);
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<List<ReservationResource>> getAllReservationsByUserId(@PathVariable UUID userId) {
        var reservations = reservationQueryService.handle(new GetAllReservationsByUserIdQuery(userId));
        var reservationResources = reservations.stream()
                .map(ReservationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(reservationResources);
    }
}
