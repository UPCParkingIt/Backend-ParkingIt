package com.parkingit.notifications.application.acl;

import com.parkingit.notifications.domain.model.entities.EmailDetails;
import com.parkingit.notifications.domain.services.EmailService;
import com.parkingit.notifications.interfaces.acl.EmailContextFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailContextFacadeImpl implements EmailContextFacade {
    private final EmailService emailService;

    @Override
    public String sendEmail(String recipient, String msgBody, String subject, String attachment) {
        var newEmailDetails = new EmailDetails(recipient, msgBody, subject, attachment);
        return emailService.sendEmail(newEmailDetails);
    }
}
