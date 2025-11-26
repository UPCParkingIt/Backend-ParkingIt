package com.parkingit.recognition.domain.services;

import com.parkingit.recognition.domain.model.entities.Vehicle;
import com.parkingit.recognition.domain.model.queries.GetAllVehiclesByLicensePlateNumberQuery;
import com.parkingit.recognition.domain.model.queries.GetAllVehiclesQuery;
import com.parkingit.recognition.domain.model.queries.GetLastVehicleQuery;
import com.parkingit.recognition.domain.model.queries.GetVehicleByIdQuery;

import java.util.List;
import java.util.Optional;

public interface VehicleQueryService {
    List<Vehicle> handle(GetAllVehiclesQuery query);
    List<Vehicle> handle(GetAllVehiclesByLicensePlateNumberQuery query);
    Optional<Vehicle> handle(GetVehicleByIdQuery query);
    Optional<Vehicle> handle(GetLastVehicleQuery query);
}
