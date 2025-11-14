package com.parkingit.recognition.application.internal.commandservices;

import com.parkingit.devices.application.internal.outboundservices.acl.ExternalIamService;
import com.parkingit.recognition.application.internal.outboundservices.acl.ExternalDeviceService;
import com.parkingit.recognition.domain.model.aggregates.FaceRecognition;
import com.parkingit.recognition.domain.model.commands.ActivateFaceRecognitionCommand;
import com.parkingit.recognition.domain.model.commands.CreateFaceRecognitionCommand;
import com.parkingit.recognition.domain.model.commands.DeactivateFaceRecognitionCommand;
import com.parkingit.recognition.domain.services.FaceRecognitionCommandService;
import com.parkingit.recognition.infrastructure.persistence.jpa.repositories.FaceRecognitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FaceRecognitionCommandServiceImpl implements FaceRecognitionCommandService {
    private final FaceRecognitionRepository faceRecognitionRepository;
    private final ExternalIamService externalIamService;
    private final ExternalDeviceService externalDeviceService;

    @Override
    public Optional<FaceRecognition> handle(CreateFaceRecognitionCommand command) {
        var userResult = externalIamService.fetchUserById(command.userId());
        var deviceResult = externalDeviceService.fetchDeviceById(command.deviceId());

        if (userResult == null || deviceResult == null) {
            throw new IllegalStateException("User and/or device not found");
        }

        var newFaceRecognition = new FaceRecognition(
                userResult,
                deviceResult,
                command.faceImageData(),
                command.faceEncodingVector()
        );

        var createdFaceRecognition = faceRecognitionRepository.save(newFaceRecognition);
        return Optional.of(createdFaceRecognition);
    }

    @Override
    public void handle(DeactivateFaceRecognitionCommand command) {
        var faceRecognitionResult = faceRecognitionRepository.findById(command.faceRecognitionId());

        if (faceRecognitionResult.isEmpty()) {
            throw new IllegalStateException("Face Recognition not found");
        }

        var faceRecognition = faceRecognitionResult.get();
        faceRecognition.deactivate();
        faceRecognitionRepository.save(faceRecognition);
    }

    @Override
    public void handle(ActivateFaceRecognitionCommand command) {
        var faceRecognitionResult = faceRecognitionRepository.findById(command.faceRecognitionId());

        if (faceRecognitionResult.isEmpty()) {
            throw new IllegalStateException("Face Recognition not found");
        }

        var faceRecognition = faceRecognitionResult.get();
        faceRecognition.activate();
        faceRecognitionRepository.save(faceRecognition);
    }
}
