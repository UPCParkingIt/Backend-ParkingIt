package com.parkingit.recognition.application.internal.queryservices;

import com.parkingit.recognition.domain.model.aggregates.FaceRecognition;
import com.parkingit.recognition.domain.model.queries.GetAllFaceRecognitionsQuery;
import com.parkingit.recognition.domain.model.queries.GetFaceRecognitionByIdQuery;
import com.parkingit.recognition.domain.services.FaceRecognitionQueryService;
import com.parkingit.recognition.infrastructure.persistence.jpa.repositories.FaceRecognitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FaceRecognitionQueryServiceImpl implements FaceRecognitionQueryService {
    private final FaceRecognitionRepository faceRecognitionRepository;

    @Override
    public List<FaceRecognition> handle(GetAllFaceRecognitionsQuery query) {
        return faceRecognitionRepository.findAll();
    }

    @Override
    public Optional<FaceRecognition> handle(GetFaceRecognitionByIdQuery query) {
        return Optional.empty();
    }
}
