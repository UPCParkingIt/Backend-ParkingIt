package com.parkingit.devices.application.internal.outboundservices.acl;

import com.parkingit.iam.domain.model.aggregates.User;
import com.parkingit.iam.interfaces.acl.IamContextFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ExternalIamService {
    private final IamContextFacade iamContextFacade;

    public User fetchUserById(UUID userId) {
        return iamContextFacade.fetchUserById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found: " + userId));
    }

    public UUID fetchUserIdByEmail(String email) {
        return iamContextFacade.fetchUserIdByEmail(email);
    }
}
