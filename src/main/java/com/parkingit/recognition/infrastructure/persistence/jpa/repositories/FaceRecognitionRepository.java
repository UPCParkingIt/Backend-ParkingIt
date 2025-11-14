package com.parkingit.recognition.infrastructure.persistence.jpa.repositories;

import com.parkingit.recognition.domain.model.aggregates.FaceRecognition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FaceRecognitionRepository extends JpaRepository<FaceRecognition, UUID> {
}
