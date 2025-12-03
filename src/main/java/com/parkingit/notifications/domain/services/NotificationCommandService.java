package com.parkingit.notifications.domain.services;

import com.parkingit.notifications.domain.model.aggregates.Notification;
import com.parkingit.notifications.domain.model.commands.CreateNotificationCommand;
import com.parkingit.notifications.domain.model.commands.DeleteNotificationCommand;

import java.util.Optional;

public interface NotificationCommandService {
    Optional<Notification> handle(CreateNotificationCommand command);
    void handle(DeleteNotificationCommand command);
}
