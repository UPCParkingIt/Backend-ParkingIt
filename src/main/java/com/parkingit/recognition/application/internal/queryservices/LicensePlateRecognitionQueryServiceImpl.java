package com.parkingit.recognition.application.internal.queryservices;

import com.parkingit.recognition.domain.model.aggregates.LicensePlateRecognition;
import com.parkingit.recognition.domain.model.queries.GetAllLicensePlateRecognitionsQuery;
import com.parkingit.recognition.domain.model.queries.GetLicensePlateRecognitionByIdQuery;
import com.parkingit.recognition.domain.services.LicensePlateRecognitionQueryService;
import com.parkingit.recognition.infrastructure.persistence.jpa.repositories.LicensePlateRecognitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LicensePlateRecognitionQueryServiceImpl implements LicensePlateRecognitionQueryService {
    private final LicensePlateRecognitionRepository licensePlateRecognitionRepository;

    @Override
    public List<LicensePlateRecognition> handle(GetAllLicensePlateRecognitionsQuery query) {
        return licensePlateRecognitionRepository.findAll();
    }

    @Override
    public Optional<LicensePlateRecognition> handle(GetLicensePlateRecognitionByIdQuery query) {
        return Optional.empty();
    }
}
