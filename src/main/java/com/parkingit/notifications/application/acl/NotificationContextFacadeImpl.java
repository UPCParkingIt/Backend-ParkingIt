package com.parkingit.notifications.application.acl;

import com.parkingit.notifications.domain.model.aggregates.Notification;
import com.parkingit.notifications.domain.model.commands.CreateNotificationCommand;
import com.parkingit.notifications.domain.model.commands.DeleteNotificationCommand;
import com.parkingit.notifications.domain.model.queries.GetAllNotificationsByUserIdQuery;
import com.parkingit.notifications.domain.services.NotificationCommandService;
import com.parkingit.notifications.domain.services.NotificationQueryService;
import com.parkingit.notifications.interfaces.acl.NotificationContextFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class NotificationContextFacadeImpl implements NotificationContextFacade {
    private final NotificationCommandService commandService;
    private final NotificationQueryService queryService;

    @Override
    public Optional<Notification> createNotification(UUID userId, String title, String message) {
        var notificationResult = commandService.handle(new CreateNotificationCommand(userId, title, message));

        return notificationResult;
    }

    public List<Notification> fetchAllNotificationsByProfileId(UUID userId) {
        return queryService.handle(new GetAllNotificationsByUserIdQuery(userId));
    }

    @Override
    public void deleteNotification(UUID notificationId) {
        commandService.handle(new DeleteNotificationCommand(notificationId));
    }
}
