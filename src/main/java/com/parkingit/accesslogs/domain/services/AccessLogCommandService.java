package com.parkingit.accesslogs.domain.services;

import com.parkingit.accesslogs.domain.model.aggregates.AccessLog;
import com.parkingit.accesslogs.domain.model.commands.CreateAccessLogCommand;

import java.util.Optional;

public interface AccessLogCommandService {
    Optional<AccessLog> handle(CreateAccessLogCommand command);
}
