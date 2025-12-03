package com.parkingit.accesslogs.interfaces.rest.transform;

import com.parkingit.accesslogs.domain.model.aggregates.AccessLog;
import com.parkingit.accesslogs.interfaces.rest.resources.AccessLogResource;

public class AccessLogResourceFromEntityAssembler {
    public static AccessLogResource toResourceFromEntity(AccessLog entity) {
        return new AccessLogResource(
                entity.getId(),
                entity.getUser().getId(),
                entity.getDevice().getId(),
                entity.getAccessType(),
                entity.getAuthenticationMethod(),
                entity.getCreatedAt()
        );
    }
}
