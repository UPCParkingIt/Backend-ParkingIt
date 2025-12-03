package com.parkingit.notifications.interfaces.rest;

import com.parkingit.devices.application.internal.outboundservices.acl.ExternalIamService;
import com.parkingit.notifications.domain.model.queries.GetAllNotificationsByUserIdQuery;
import com.parkingit.notifications.domain.services.NotificationCommandService;
import com.parkingit.notifications.domain.services.NotificationQueryService;
import com.parkingit.notifications.interfaces.rest.resources.NotificationResource;
import com.parkingit.notifications.interfaces.rest.transform.NotificationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/notifications", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Notifications", description = "Notifications Management Endpoint")
public class NotificationsController {
    private final NotificationCommandService commandService;
    private final NotificationQueryService queryService;
    private final ExternalIamService externalIamService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<NotificationResource>> getAllNotificationsByProfileId(@PathVariable UUID userId) {
        var userResult = externalIamService.fetchUserById(userId);

        if (userResult == null) {
            throw new IllegalArgumentException("User not found for the provided ID");
        }

        var getAllNotificationsQuery = new GetAllNotificationsByUserIdQuery(userResult.getId());
        var notifications = queryService.handle(getAllNotificationsQuery);
        if (notifications.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        var notificationResources = notifications.stream()
                .map(NotificationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(notificationResources);
    }
}
