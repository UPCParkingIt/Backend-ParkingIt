package com.parkingit.iam.domain.model.commands;

import com.parkingit.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(
        String email,
        String password,
        String firstName,
        String lastName,
        String phoneNumber,
        String profilePhotoUrl,
        String dniNumber,
        List<Role> roles) {
}
