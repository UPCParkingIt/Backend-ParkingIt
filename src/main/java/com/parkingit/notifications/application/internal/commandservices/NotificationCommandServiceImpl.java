package com.parkingit.notifications.application.internal.commandservices;

import com.parkingit.devices.application.internal.outboundservices.acl.ExternalIamService;
import com.parkingit.notifications.domain.model.aggregates.Notification;
import com.parkingit.notifications.domain.model.commands.CreateNotificationCommand;
import com.parkingit.notifications.domain.model.commands.DeleteNotificationCommand;
import com.parkingit.notifications.domain.services.NotificationCommandService;
import com.parkingit.notifications.infrastructure.persistence.jpa.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class NotificationCommandServiceImpl implements NotificationCommandService {
    private final NotificationRepository repository;
    private final ExternalIamService externalIamService;

    @Override
    public Optional<Notification> handle(CreateNotificationCommand command) {
        var userResult = externalIamService.fetchUserById(command.userId());

        if (userResult == null) {
            throw new IllegalArgumentException("User not found with ID: " + command.userId());
        }

        var newNotification = new Notification(
                userResult,
                command.title(),
                command.message()
        );

        try {
            var savedNotification = repository.save(newNotification);
            return Optional.of(savedNotification);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error creating notification: " + e.getMessage(), e);
        }
    }

    @Override
    public void handle(DeleteNotificationCommand command) {
        try {
            var notificationResult = repository.findById(command.id());

            if (notificationResult.isEmpty()) {
                throw new IllegalArgumentException("Notification not found with ID: " + command.id());
            }

            repository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting notification: " + e.getMessage(), e);
        }
    }
}
