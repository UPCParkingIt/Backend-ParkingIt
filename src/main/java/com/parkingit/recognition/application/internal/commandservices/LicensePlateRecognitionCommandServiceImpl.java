package com.parkingit.recognition.application.internal.commandservices;

import com.parkingit.devices.application.internal.outboundservices.acl.ExternalIamService;
import com.parkingit.recognition.application.internal.outboundservices.acl.ExternalDeviceService;
import com.parkingit.recognition.domain.model.aggregates.LicensePlateRecognition;
import com.parkingit.recognition.domain.model.commands.ActivateLicensePlateRecognitionCommand;
import com.parkingit.recognition.domain.model.commands.CreateLicensePlateRecognitionCommand;
import com.parkingit.recognition.domain.model.commands.DeactivateLicensePlateRecognitionCommand;
import com.parkingit.recognition.domain.model.queries.GetVehicleByIdQuery;
import com.parkingit.recognition.domain.services.LicensePlateRecognitionCommandService;
import com.parkingit.recognition.domain.services.VehicleQueryService;
import com.parkingit.recognition.infrastructure.persistence.jpa.repositories.LicensePlateRecognitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LicensePlateRecognitionCommandServiceImpl implements LicensePlateRecognitionCommandService {
    private final LicensePlateRecognitionRepository licensePlateRecognitionRepository;
    private final ExternalIamService externalIamService;
    private final ExternalDeviceService externalDeviceService;
    private final VehicleQueryService vehicleQueryService;

    @Override
    public Optional<LicensePlateRecognition> handle(CreateLicensePlateRecognitionCommand query) {
        var userResult = externalIamService.fetchUserById(query.userId());
        var deviceResult = externalDeviceService.fetchDeviceById(query.deviceId());
        var vehicleResult = vehicleQueryService.handle(new GetVehicleByIdQuery(query.vehicleId()));

        if (userResult == null) {
            throw new IllegalStateException("User not found");
        }

        if (deviceResult == null) {
            throw new IllegalStateException("Device not found");
        }

        if (vehicleResult.isEmpty()) {
            throw new IllegalStateException("Vehicle not found");
        }

        var newLicensePlateRecognition = new LicensePlateRecognition(
                userResult,
                deviceResult,
                vehicleResult.get(),
                query.plateImageData()
        );

        var savedLicensePlateRecognition = licensePlateRecognitionRepository.save(newLicensePlateRecognition);
        return Optional.of(savedLicensePlateRecognition);
    }

    @Override
    public void handle(DeactivateLicensePlateRecognitionCommand command) {
        var licensePlateRecognitionResult = licensePlateRecognitionRepository.findById(command.licensePlateRecognitionId());

        if (licensePlateRecognitionResult.isEmpty()) {
            throw new IllegalStateException("License Plate Recognition not found");
        }

        var licensePlateRecognition = licensePlateRecognitionResult.get();
        licensePlateRecognition.deactivate();
        licensePlateRecognitionRepository.save(licensePlateRecognition);
    }

    @Override
    public void handle(ActivateLicensePlateRecognitionCommand command) {
        var licensePlateRecognitionResult = licensePlateRecognitionRepository.findById(command.licensePlateRecognitionId());

        if (licensePlateRecognitionResult.isEmpty()) {
            throw new IllegalStateException("License Plate Recognition not found");
        }

        var licensePlateRecognition = licensePlateRecognitionResult.get();
        licensePlateRecognition.activate();
        licensePlateRecognitionRepository.save(licensePlateRecognition);
    }
}
