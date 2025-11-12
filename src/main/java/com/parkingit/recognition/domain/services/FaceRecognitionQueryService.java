package com.parkingit.recognition.domain.services;

import com.parkingit.recognition.domain.model.aggregates.FaceRecognition;
import com.parkingit.recognition.domain.model.queries.GetAllFaceRecognitionsQuery;
import com.parkingit.recognition.domain.model.queries.GetFaceRecognitionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface FaceRecognitionQueryService {
    List<FaceRecognition> handle(GetAllFaceRecognitionsQuery query);
    Optional<FaceRecognition> handle(GetFaceRecognitionByIdQuery query);
}
