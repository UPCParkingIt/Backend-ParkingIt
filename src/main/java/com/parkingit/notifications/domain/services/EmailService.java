package com.parkingit.notifications.domain.services;

import com.parkingit.notifications.domain.model.entities.EmailDetails;

public interface EmailService {
    String sendEmail(EmailDetails details);
    String sendEmailWithAttachment(EmailDetails details);
}
