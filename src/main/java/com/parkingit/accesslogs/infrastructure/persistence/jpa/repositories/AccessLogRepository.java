package com.parkingit.accesslogs.infrastructure.persistence.jpa.repositories;

import com.parkingit.accesslogs.domain.model.aggregates.AccessLog;
import com.parkingit.accesslogs.domain.model.valueobjects.AccessResult;
import com.parkingit.accesslogs.domain.model.valueobjects.AccessType;
import com.parkingit.accesslogs.domain.model.valueobjects.AuthenticationMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AccessLogRepository extends JpaRepository<AccessLog, UUID> {
    List<AccessLog> findAllByUser_Id(UUID userId);

    List<AccessLog> findAllByDevice_Id(UUID deviceId);

    List<AccessLog> findAllByAccessType(AccessType accessType);

    List<AccessLog> findAllByAuthenticationMethod(AuthenticationMethod authenticationMethod);

    List<AccessLog> findAllByAccessResult(AccessResult accessResult);
}
