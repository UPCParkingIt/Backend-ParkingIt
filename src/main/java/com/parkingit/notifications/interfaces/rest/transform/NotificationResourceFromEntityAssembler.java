package com.parkingit.notifications.interfaces.rest.transform;

import com.parkingit.notifications.domain.model.aggregates.Notification;
import com.parkingit.notifications.interfaces.rest.resources.NotificationResource;

public class NotificationResourceFromEntityAssembler {
    public static NotificationResource toResourceFromEntity(Notification entity) {
        return new NotificationResource(
                entity.getId(),
                entity.getUser().getId(),
                entity.getTitle(),
                entity.getMessage()
        );
    }
}
