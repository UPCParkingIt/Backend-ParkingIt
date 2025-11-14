package com.parkingit.accesslogs.domain.services;

import com.parkingit.accesslogs.domain.model.aggregates.AccessLog;
import com.parkingit.accesslogs.domain.model.queries.*;

import java.util.List;

public interface AccessLogQueryService {
    List<AccessLog> handle(GetAllAccessLogsQuery query);
    List<AccessLog> handle(GetAllAccessLogsByUserIdQuery query);
    List<AccessLog> handle(GetAllAccessLogsByDeviceIdQuery query);
    List<AccessLog> handle(GetAllAccessLogsByAccessTypeQuery query);
    List<AccessLog> handle(GetAllAccessLogsByAuthenticationMethodQuery query);
    List<AccessLog> handle(GetAllAccessLogsByAccessResultQuery query);
}
