package com.parkingit.iam.interfaces.rest.resources;

public record CreateUserResource(
        String email,
        String passwordHash
) {
}
