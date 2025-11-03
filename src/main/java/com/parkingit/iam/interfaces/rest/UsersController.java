package com.parkingit.iam.interfaces.rest;

import com.parkingit.iam.domain.model.queries.GetAllUsersByRoleQuery;
import com.parkingit.iam.domain.model.queries.GetAllUsersQuery;
import com.parkingit.iam.domain.model.valueobjects.Roles;
import com.parkingit.iam.domain.services.UserQueryService;
import com.parkingit.iam.interfaces.rest.resources.UserResource;
import com.parkingit.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Users Endpoints")
public class UsersController {
    private final UserQueryService userQueryService;

    /**
     * Fetches all users.
     *
     * @return a list of all user resources.
     */
    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var users = userQueryService.handle(new GetAllUsersQuery());
        List<UserResource> resources = users.stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
