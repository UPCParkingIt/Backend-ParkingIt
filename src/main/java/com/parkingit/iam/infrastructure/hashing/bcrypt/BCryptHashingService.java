package com.parkingit.iam.infrastructure.hashing.bcrypt;

import com.parkingit.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
