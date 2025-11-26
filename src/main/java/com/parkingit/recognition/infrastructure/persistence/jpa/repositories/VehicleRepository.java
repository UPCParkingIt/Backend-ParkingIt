package com.parkingit.recognition.infrastructure.persistence.jpa.repositories;

import com.parkingit.recognition.domain.model.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    List<Vehicle> findAllByLicensePlateNumber(String licensePlateNumber);

    @Query("SELECT r FROM Vehicle r ORDER BY r.createdAt DESC LIMIT 1")
    Optional<Vehicle> findLatestVehicle();
}
