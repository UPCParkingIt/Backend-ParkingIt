package com.parkingit.accesslogs.interfaces.rest.transform;

import com.parkingit.accesslogs.domain.model.commands.CreateAccessLogCommand;
import com.parkingit.accesslogs.interfaces.rest.resources.CreateAccessLogResource;

public class CreateAccessLogCommandFromResourceAssembler {
    public static CreateAccessLogCommand toCommandFromResource(CreateAccessLogResource resource) {
        return new CreateAccessLogCommand(
                resource.userId(),
                resource.deviceId(),
                resource.accessType(),
                resource.authenticationMethod()
        );
    }
}
