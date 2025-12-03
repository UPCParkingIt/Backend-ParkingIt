package com.parkingit.notifications.interfaces.rest.transform;

import com.parkingit.notifications.domain.model.commands.CreateNotificationCommand;
import com.parkingit.notifications.interfaces.rest.resources.CreateNotificationResource;

public class CreateNotificationCommandFromResourceAssembler {
    public static CreateNotificationCommand toCommandFromResource(CreateNotificationResource resource) {
        return new CreateNotificationCommand(
                resource.userId(),
                resource.title(),
                resource.message()
        );
    }
}
