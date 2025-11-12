package com.parkingit.accesslogs.domain.model.aggregates;

import com.parkingit.accesslogs.domain.model.valueobjects.AccessResult;
import com.parkingit.accesslogs.domain.model.valueobjects.AccessType;
import com.parkingit.accesslogs.domain.model.valueobjects.AuthenticationMethod;
import com.parkingit.devices.domain.model.aggregates.Device;
import com.parkingit.iam.domain.model.aggregates.User;
import com.parkingit.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AccessLog extends AuditableAbstractAggregateRoot<AccessLog> {
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private Device device;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccessType accessType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthenticationMethod authenticationMethod;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccessResult accessResult;

    public AccessLog() {}

    public AccessLog(User user, Device device, AccessType accessType, AuthenticationMethod authenticationMethod) {
        this.user = user;
        this.device = device;
        this.accessType = accessType;
        this.authenticationMethod = authenticationMethod;
        this.accessResult = AccessResult.PENDING;
    }
}
