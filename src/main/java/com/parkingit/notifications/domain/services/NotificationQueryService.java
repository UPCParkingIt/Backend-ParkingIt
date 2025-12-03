package com.parkingit.notifications.domain.services;

import com.parkingit.notifications.domain.model.aggregates.Notification;
import com.parkingit.notifications.domain.model.queries.GetAllNotificationsByUserIdQuery;

import java.util.List;

public interface NotificationQueryService {
    List<Notification> handle(GetAllNotificationsByUserIdQuery query);
}
