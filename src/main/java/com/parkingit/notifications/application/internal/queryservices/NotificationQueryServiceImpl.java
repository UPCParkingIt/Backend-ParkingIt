package com.parkingit.notifications.application.internal.queryservices;

import com.parkingit.notifications.domain.model.aggregates.Notification;
import com.parkingit.notifications.domain.model.queries.GetAllNotificationsByUserIdQuery;
import com.parkingit.notifications.domain.services.NotificationQueryService;
import com.parkingit.notifications.infrastructure.persistence.jpa.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationQueryServiceImpl implements NotificationQueryService {
    private final NotificationRepository repository;

    @Override
    public List<Notification> handle(GetAllNotificationsByUserIdQuery query) {
        return repository.findAllByUser_Id(query.userId());
    }
}
