package com.parkingit.iam.domain.services;

import com.parkingit.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
  void handle(SeedRolesCommand command);
}
