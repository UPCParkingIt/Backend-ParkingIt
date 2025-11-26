package com.parkingit.recognition.application.internal.commandservices;

import com.parkingit.recognition.domain.model.commands.ActivateRecognitionProcessCommand;
import com.parkingit.recognition.domain.model.commands.DeactivateRecognitionProcessCommand;
import com.parkingit.recognition.domain.model.entities.RecognitionProcess;
import com.parkingit.recognition.domain.services.RecognitionProcessCommandService;
import com.parkingit.recognition.infrastructure.persistence.jpa.repositories.RecognitionProcessRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RecognitionProcessCommandServiceImpl implements RecognitionProcessCommandService {
    private final RecognitionProcessRepository recognitionProcessRepository;

    @Override
    public Optional<Boolean> handle(ActivateRecognitionProcessCommand command) {
        var process = new RecognitionProcess();
        process.startProcess();
        recognitionProcessRepository.save(process);
        return Optional.of(Boolean.TRUE);
    }

    @Override
    public Optional<Boolean> handle(DeactivateRecognitionProcessCommand command) {
        var process = new RecognitionProcess();
        process.startProcess();
        recognitionProcessRepository.save(process);
        return Optional.of(Boolean.FALSE);
    }
}
