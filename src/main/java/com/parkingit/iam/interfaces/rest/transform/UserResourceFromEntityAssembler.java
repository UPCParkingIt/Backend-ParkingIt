package com.parkingit.iam.interfaces.rest.transform;

import com.parkingit.iam.domain.model.aggregates.User;
import com.parkingit.iam.domain.model.entities.Role;
import com.parkingit.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream()
                .map(Role::getStringName)
                .toList();

        return new UserResource(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getProfilePhotoUrl(),
                user.getDniNumber(),
                roles,
                user.getCreatedAt()
        );
    }
}
