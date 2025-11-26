package com.parkingit.recognition.domain.services;

import com.parkingit.recognition.domain.model.commands.GetCurrentRecognitionProcessStatusQuery;

import java.util.Optional;

public interface RecognitionProcessQueryService {
    Optional<Boolean> handle(GetCurrentRecognitionProcessStatusQuery query);
}
