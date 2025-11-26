package com.parkingit.recognition.application.internal.queryservices;

import com.parkingit.recognition.domain.model.commands.GetCurrentRecognitionProcessStatusQuery;
import com.parkingit.recognition.domain.services.RecognitionProcessQueryService;
import com.parkingit.recognition.infrastructure.persistence.jpa.repositories.RecognitionProcessRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RecognitionProcessQueryServiceImpl implements RecognitionProcessQueryService {
    private final RecognitionProcessRepository recognitionProcessRepository;

    @Override
    public Optional<Boolean> handle(GetCurrentRecognitionProcessStatusQuery query) {
        var process = recognitionProcessRepository.findLatestProcess();
        if (process.isEmpty()) return Optional.of(false);

        var status = process.get().getIsActive();

        //if (status) {
        //    process.get().stopProcess();
        //    recognitionProcessRepository.save(process.get());
        //}

        return Optional.of(status);
    }
}
