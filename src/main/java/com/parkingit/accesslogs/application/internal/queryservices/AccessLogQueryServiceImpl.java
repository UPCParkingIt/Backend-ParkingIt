package com.parkingit.accesslogs.application.internal.queryservices;

import com.parkingit.accesslogs.domain.model.aggregates.AccessLog;
import com.parkingit.accesslogs.domain.model.queries.*;
import com.parkingit.accesslogs.domain.services.AccessLogQueryService;
import com.parkingit.accesslogs.infrastructure.persistence.jpa.repositories.AccessLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccessLogQueryServiceImpl implements AccessLogQueryService {
    private final AccessLogRepository accessLogRepository;

    @Override
    public List<AccessLog> handle(GetAllAccessLogsQuery query) {
        return accessLogRepository.findAll();
    }

    @Override
    public List<AccessLog> handle(GetAllAccessLogsByUserIdQuery query) {
        return accessLogRepository.findAllByUser_Id(query.userId());
    }

    @Override
    public List<AccessLog> handle(GetAllAccessLogsByDeviceIdQuery query) {
        return accessLogRepository.findAllByDevice_Id(query.deviceId());
    }

    @Override
    public List<AccessLog> handle(GetAllAccessLogsByAccessTypeQuery query) {
        return accessLogRepository.findAllByAccessType(query.accessType());
    }

    @Override
    public List<AccessLog> handle(GetAllAccessLogsByAuthenticationMethodQuery query) {
        return accessLogRepository.findAllByAuthenticationMethod(query.authenticationMethod());
    }

    @Override
    public List<AccessLog> handle(GetAllAccessLogsByAccessResultQuery query) {
        return accessLogRepository.findAllByAccessResult(query.accessResult());
    }
}
