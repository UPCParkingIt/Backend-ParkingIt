package com.parkingit.devices.interfaces.rest;

import com.parkingit.devices.application.internal.outboundservices.acl.ExternalIamService;
import com.parkingit.devices.domain.model.commands.ActivateDeviceCommand;
import com.parkingit.devices.domain.model.commands.CreateDeviceCommand;
import com.parkingit.devices.domain.model.commands.DeactivateDeviceCommand;
import com.parkingit.devices.domain.model.commands.UpdateDeviceCommand;
import com.parkingit.devices.domain.model.queries.GetAllDevicesByNameQuery;
import com.parkingit.devices.domain.model.queries.GetAllDevicesByUserIdQuery;
import com.parkingit.devices.domain.model.queries.GetAllDevicesQuery;
import com.parkingit.devices.domain.model.queries.GetDeviceByIdQuery;
import com.parkingit.devices.domain.services.DeviceCommandService;
import com.parkingit.devices.domain.services.DeviceQueryService;
import com.parkingit.devices.interfaces.rest.resources.CreateDeviceResource;
import com.parkingit.devices.interfaces.rest.resources.DeviceResource;
import com.parkingit.devices.interfaces.rest.resources.UpdateDeviceResource;
import com.parkingit.devices.interfaces.rest.transform.CreateDeviceCommandFromResourceAssembler;
import com.parkingit.devices.interfaces.rest.transform.DeviceResourceFromEntityAssembler;
import com.parkingit.devices.interfaces.rest.transform.UpdateDeviceCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@AllArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/devices", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Devices", description = "Devices Management Endpoint")
public class DevicesController {
    private final DeviceCommandService deviceCommandService;
    private final DeviceQueryService deviceQueryService;
    private final ExternalIamService externalIamService;

    @PostMapping
    public ResponseEntity<DeviceResource> createDevice(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CreateDeviceResource resource) {
        var email = userDetails.getUsername();
        var userId = externalIamService.fetchUserIdByEmail(email);

        var createDeviceCommand = new CreateDeviceCommand(
                resource.deviceName(),
                userId
        );

        var device = deviceCommandService.handle(createDeviceCommand);

        if (device.isEmpty()) throw new NoSuchElementException("Device not found: " + email);

        var deviceResource = DeviceResourceFromEntityAssembler.toResourceFromEntity(device.get());
        return new ResponseEntity<>(deviceResource, HttpStatus.CREATED);
    }

    @DeleteMapping("/deactivate/{deviceId}")
    public ResponseEntity<?> deactivateDevice(@PathVariable UUID deviceId) {
        var deactivateDeviceCommand = new DeactivateDeviceCommand(deviceId);

        deviceCommandService.handle(deactivateDeviceCommand);

        return ResponseEntity.ok("Device deleted successfully");
    }

    @PatchMapping("/activate/{deviceId}")
    public ResponseEntity<?> activateDevice(@PathVariable UUID deviceId) {
        var activateDEviceCommand = new ActivateDeviceCommand(deviceId);

        deviceCommandService.handle(activateDEviceCommand);

        return ResponseEntity.ok("Device activated successfully");
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<DeviceResource> getDeviceById(@PathVariable UUID deviceId) {
        var getDeviceByIdQuery = new GetDeviceByIdQuery(deviceId);

        var device = deviceQueryService.handle(getDeviceByIdQuery);

        if (device.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var deviceResource = DeviceResourceFromEntityAssembler.toResourceFromEntity(device.get());
        return ResponseEntity.ok(deviceResource);
    }
    
    @GetMapping
    public ResponseEntity<List<DeviceResource>> getAllDevices() {
        var getAllDevicesQuery = new GetAllDevicesQuery();
        
        var devices = deviceQueryService.handle(getAllDevicesQuery);
        
        var deviceResources = devices.stream()
                .map(DeviceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(deviceResources);
    }
    
    @GetMapping("/user")
    public ResponseEntity<List<DeviceResource>> getAllDevicesByUserId(@AuthenticationPrincipal UserDetails userDetails) {
        var email = userDetails.getUsername();
        var userId = externalIamService.fetchUserIdByEmail(email);

        var getAllDevicesByUserIdQuery = new GetAllDevicesByUserIdQuery(userId);

        var devices = deviceQueryService.handle(getAllDevicesByUserIdQuery);

        var deviceResources = devices.stream()
                .map(DeviceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(deviceResources);
    }

    @GetMapping("/name/{deviceName}")
    public ResponseEntity<List<DeviceResource>> getAllDevicesByName(@PathVariable String deviceName) {
        var getAllDevicesByNameQuery = new GetAllDevicesByNameQuery(deviceName);

        var devices = deviceQueryService.handle(getAllDevicesByNameQuery);

        var deviceResources = devices.stream()
                .map(DeviceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(deviceResources);
    }

    @PutMapping("/{deviceId}")
    public ResponseEntity<DeviceResource> updateDevice(@PathVariable UUID deviceId, @RequestBody UpdateDeviceResource resource) {
        var updateCommand = UpdateDeviceCommandFromResourceAssembler.toCommandFromResource(deviceId, resource);

        var updated = deviceCommandService.handle(updateCommand);

        if (updated.isEmpty()) return ResponseEntity.notFound().build();

        var deviceResource = DeviceResourceFromEntityAssembler.toResourceFromEntity(updated.get());
        return ResponseEntity.ok(deviceResource);
    }
}
