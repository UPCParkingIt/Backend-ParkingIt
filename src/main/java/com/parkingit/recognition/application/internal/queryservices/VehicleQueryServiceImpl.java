package com.parkingit.recognition.application.internal.queryservices;

import com.parkingit.recognition.domain.model.entities.Vehicle;
import com.parkingit.recognition.domain.model.queries.GetAllVehiclesByLicensePlateNumberQuery;
import com.parkingit.recognition.domain.model.queries.GetAllVehiclesQuery;
import com.parkingit.recognition.domain.model.queries.GetLastVehicleQuery;
import com.parkingit.recognition.domain.model.queries.GetVehicleByIdQuery;
import com.parkingit.recognition.domain.services.VehicleQueryService;
import com.parkingit.recognition.infrastructure.persistence.jpa.repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VehicleQueryServiceImpl implements VehicleQueryService {
    private final VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> handle(GetAllVehiclesQuery query) {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> handle(GetAllVehiclesByLicensePlateNumberQuery query) {
        return vehicleRepository.findAllByLicensePlateNumber(query.licensePlateNumber());
    }

    @Override
    public Optional<Vehicle> handle(GetVehicleByIdQuery query) {
        return Optional.empty();
    }

    @Override
    public Optional<Vehicle> handle(GetLastVehicleQuery query) {
        return vehicleRepository.findLatestVehicle();
    }
}
