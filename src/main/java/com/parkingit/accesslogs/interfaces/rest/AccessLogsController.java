package com.parkingit.accesslogs.interfaces.rest;

import com.parkingit.accesslogs.domain.model.queries.GetAllAccessLogsQuery;
import com.parkingit.accesslogs.domain.services.AccessLogCommandService;
import com.parkingit.accesslogs.domain.services.AccessLogQueryService;
import com.parkingit.accesslogs.interfaces.rest.resources.AccessLogResource;
import com.parkingit.accesslogs.interfaces.rest.resources.CreateAccessLogResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/access-logs", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Access Logs", description = "Access Logs Management Endpoint")
public class AccessLogsController {
    private final AccessLogCommandService accessLogCommandService;
    private final AccessLogQueryService accessLogQueryService;

    @PostMapping
    public ResponseEntity<AccessLogResource> createAccessLog(@RequestBody CreateAccessLogResource resource) {
        var createAccessLogCommand = com.parkingit.accesslogs.interfaces.rest.transform.CreateAccessLogCommandFromResourceAssembler.toCommandFromResource(resource);
        var accessLog = accessLogCommandService.handle(createAccessLogCommand);

        if (accessLog.isEmpty()) {
            throw new IllegalArgumentException("Access Log could not be created");
        }

        var accessLogResource = com.parkingit.accesslogs.interfaces.rest.transform.AccessLogResourceFromEntityAssembler.toResourceFromEntity(accessLog.get());
        return new ResponseEntity<>(accessLogResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AccessLogResource>> getAllAccessLogs() {
        var accessLogs = accessLogQueryService.handle(new GetAllAccessLogsQuery());
        var accessLogResources = accessLogs.stream()
                .map(com.parkingit.accesslogs.interfaces.rest.transform.AccessLogResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new ResponseEntity<>(accessLogResources, HttpStatus.OK);
    }
}
