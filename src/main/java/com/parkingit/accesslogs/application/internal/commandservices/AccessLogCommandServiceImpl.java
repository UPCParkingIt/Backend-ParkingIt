package com.parkingit.accesslogs.application.internal.commandservices;

import com.parkingit.accesslogs.domain.model.aggregates.AccessLog;
import com.parkingit.accesslogs.domain.model.commands.CreateAccessLogCommand;
import com.parkingit.accesslogs.domain.services.AccessLogCommandService;
import com.parkingit.accesslogs.infrastructure.persistence.jpa.repositories.AccessLogRepository;
import com.parkingit.devices.application.internal.outboundservices.acl.ExternalIamService;
import com.parkingit.recognition.application.internal.outboundservices.acl.ExternalDeviceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccessLogCommandServiceImpl implements AccessLogCommandService {
    private final AccessLogRepository accessLogRepository;
    private final ExternalIamService externalIamService;
    private final ExternalDeviceService externalDeviceService;

    @Override
    public Optional<AccessLog> handle(CreateAccessLogCommand command) {
        try {
            var user = externalIamService.fetchUserById(command.userId());
            var device = externalDeviceService.fetchDeviceById(command.deviceId());

            if (user == null) throw new IllegalArgumentException("User not found with ID: " + command.userId());
            if (device == null) throw new IllegalArgumentException("Device not found with ID: " + command.deviceId());

            var newAccessLog = new AccessLog(
                    user,
                    device,
                    command.accessType(),
                    command.authenticationMethod()
            );

            var savedAccessLog = accessLogRepository.save(newAccessLog);
            return Optional.of(savedAccessLog);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create access log: " + e.getMessage(), e);
        }
    }
}
