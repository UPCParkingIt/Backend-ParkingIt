package com.parkingit.recognition.interfaces.rest;

import com.parkingit.recognition.domain.model.entities.Vehicle;
import com.parkingit.recognition.domain.model.queries.GetAllVehiclesByLicensePlateNumberQuery;
import com.parkingit.recognition.domain.model.queries.GetAllVehiclesQuery;
import com.parkingit.recognition.domain.model.queries.GetVehicleByIdQuery;
import com.parkingit.recognition.domain.services.VehicleCommandService;
import com.parkingit.recognition.domain.services.VehicleQueryService;
import com.parkingit.recognition.interfaces.rest.resources.CreateVehicleResource;
import com.parkingit.recognition.interfaces.rest.resources.VehicleResource;
import com.parkingit.recognition.interfaces.rest.transform.CreateVehicleCommandFromResourceAssembler;
import com.parkingit.recognition.interfaces.rest.transform.VehicleResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Vehicles", description = "Vehicles Management Endpoint")
public class VehiclesController {
    private final VehicleCommandService vehicleCommandService;
    private final VehicleQueryService vehicleQueryService;

    @PostMapping
    public ResponseEntity<VehicleResource> createVehicle(@RequestBody CreateVehicleResource resource) {
        var createVehicleCommand = CreateVehicleCommandFromResourceAssembler.toCommandFromResource(resource);
        var vehicle = vehicleCommandService.handle(createVehicleCommand);
        var vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle.get());
        return new ResponseEntity<>(vehicleResource, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResource> getVehicleById(@PathVariable UUID id) {
        var getVehicleByIdQuery = new GetVehicleByIdQuery(id);
        var vehicle = vehicleQueryService.handle(getVehicleByIdQuery);
        var vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle.get());
        return ResponseEntity.ok(vehicleResource);
    }

    @GetMapping
    public ResponseEntity<List<VehicleResource>> getAllVehicles() {
        var getAllVehiclesQuery = new GetAllVehiclesQuery();
        var vehicles = vehicleQueryService.handle(getAllVehiclesQuery);
        var vehicleResources = vehicles.stream()
                .map(VehicleResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(vehicleResources);
    }

    @GetMapping("/license-plate/{licensePlateNumber}")
    public ResponseEntity<List<VehicleResource>> getAllVehiclesByLicensePlateNumber(@PathVariable String licensePlateNumber) {
        var getAllVehiclesByLicensePlateNumberQuery = new GetAllVehiclesByLicensePlateNumberQuery(licensePlateNumber);
        var vehicles = vehicleQueryService.handle(getAllVehiclesByLicensePlateNumberQuery);
        var vehicleResources = vehicles.stream()
                .map(VehicleResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(vehicleResources);
    }
}
