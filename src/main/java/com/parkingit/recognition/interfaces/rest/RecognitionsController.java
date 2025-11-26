package com.parkingit.recognition.interfaces.rest;

import com.parkingit.recognition.domain.model.commands.ActivateRecognitionProcessCommand;
import com.parkingit.recognition.domain.model.commands.DeactivateRecognitionProcessCommand;
import com.parkingit.recognition.domain.model.commands.GetCurrentRecognitionProcessStatusQuery;
import com.parkingit.recognition.domain.model.queries.GetAllFaceRecognitionsQuery;
import com.parkingit.recognition.domain.model.queries.GetAllLicensePlateRecognitionsQuery;
import com.parkingit.recognition.domain.model.queries.GetFaceRecognitionByIdQuery;
import com.parkingit.recognition.domain.model.queries.GetLicensePlateRecognitionByIdQuery;
import com.parkingit.recognition.domain.services.*;
import com.parkingit.recognition.interfaces.rest.resources.CreateFRResource;
import com.parkingit.recognition.interfaces.rest.resources.CreateLPRResource;
import com.parkingit.recognition.interfaces.rest.resources.FRResource;
import com.parkingit.recognition.interfaces.rest.resources.LPRResource;
import com.parkingit.recognition.interfaces.rest.transform.CreateFRCommandFromResourceAssembler;
import com.parkingit.recognition.interfaces.rest.transform.CreateLPRCommandFromResourceAssembler;
import com.parkingit.recognition.interfaces.rest.transform.FRResourceFromEntityAssembler;
import com.parkingit.recognition.interfaces.rest.transform.LPRResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/recognitions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Recognitions", description = "Recognitions Management Endpoint")
public class RecognitionsController {
    private final FaceRecognitionCommandService frCommandService;
    private final FaceRecognitionQueryService frQueryService;
    private final LicensePlateRecognitionCommandService lprCommandService;
    private final LicensePlateRecognitionQueryService lprQueryService;
    private final RecognitionProcessCommandService recognitionProcessCommandService;
    private final RecognitionProcessQueryService recognitionProcessQueryService;

    @PostMapping("/fr")
    public ResponseEntity<FRResource>
    createFaceRecognition(@RequestBody CreateFRResource resource) {
        var createFRCommand = CreateFRCommandFromResourceAssembler.toCommandFromResource(resource);
        var FR = frCommandService.handle(createFRCommand);
        var frResource = FRResourceFromEntityAssembler.toResourceFromEntity(FR.get());
        return ResponseEntity.ok(frResource);
    }

    @GetMapping("/fr/{id}")
    public ResponseEntity<FRResource> getFaceRecognitionById(@PathVariable UUID id) {
        var getFRByIdQuery = new GetFaceRecognitionByIdQuery(id);
        var FR = frQueryService.handle(getFRByIdQuery);
        var frResource = FRResourceFromEntityAssembler.toResourceFromEntity(FR.get());
        return ResponseEntity.ok(frResource);
    }

    @GetMapping("/fr")
    public ResponseEntity<List<FRResource>> getAllFaceRecognitions() {
        var getAllFRQuery = new GetAllFaceRecognitionsQuery();
        var FRs = frQueryService.handle(getAllFRQuery);
        var frResources = FRs.stream()
                .map(FRResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(frResources);
    }

    @PostMapping("/lpr")
    public ResponseEntity<LPRResource> createLicensePlateRecognition(@RequestBody CreateLPRResource resource) {
        var createLPRCommand = CreateLPRCommandFromResourceAssembler.toCommandFromResource(resource);
        var LPR = lprCommandService.handle(createLPRCommand);
        var frResource = LPRResourceFromEntityAssembler.toResourceFromEntity(LPR.get());
        return ResponseEntity.ok(frResource);
    }

    @GetMapping("/lpr/{id}")
    public ResponseEntity<LPRResource> getLicensePlateRecognitionById(@PathVariable UUID id) {
        var getLPRByIdQuery = new GetLicensePlateRecognitionByIdQuery(id);
        var LPR = lprQueryService.handle(getLPRByIdQuery);
        var lprResource = LPRResourceFromEntityAssembler.toResourceFromEntity(LPR.get());
        return ResponseEntity.ok(lprResource);
    }

    @GetMapping("/lpr")
    public ResponseEntity<List<LPRResource>> getAllLicensePlateRecognitions() {
        var LPRs = lprQueryService.handle(new GetAllLicensePlateRecognitionsQuery());
        var lprResources = LPRs.stream()
                .map(LPRResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(lprResources);
    }

    @PostMapping("/process/activate")
    public ResponseEntity<Boolean> activateRecognitionProcess() {
        var command = new ActivateRecognitionProcessCommand();
        var result = recognitionProcessCommandService.handle(command);
        return ResponseEntity.ok(result.orElse(false));
    }

    @DeleteMapping("/process/deactivate")
    public ResponseEntity<Boolean> deactivateRecognitionProcess() {
        var command = new DeactivateRecognitionProcessCommand();
        var result = recognitionProcessCommandService.handle(command);
        return ResponseEntity.ok(result.orElse(false));
    }

    @GetMapping("/process/status")
    public ResponseEntity<Boolean> getRecognitionProcessStatus() {
        var query = new GetCurrentRecognitionProcessStatusQuery();
        var isActive = recognitionProcessQueryService.handle(query);
        return ResponseEntity.ok(isActive.orElse(false));
    }
}
