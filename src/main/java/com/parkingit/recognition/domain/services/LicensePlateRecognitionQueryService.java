package com.parkingit.recognition.domain.services;

import com.parkingit.recognition.domain.model.aggregates.LicensePlateRecognition;
import com.parkingit.recognition.domain.model.queries.GetAllLicensePlateRecognitionsQuery;
import com.parkingit.recognition.domain.model.queries.GetLicensePlateRecognitionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface LicensePlateRecognitionQueryService {
    List<LicensePlateRecognition> handle(GetAllLicensePlateRecognitionsQuery query);
    Optional<LicensePlateRecognition> handle(GetLicensePlateRecognitionByIdQuery query);
}
