package com.parkingit.notifications.application.internal.outboundservices.acl;

import com.parkingit.notifications.interfaces.acl.EmailContextFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExternalEmailService {
    private final EmailContextFacade emailContextFacade;

    public void sendEmail(String recipient, String msgBody, String subject, String attachment) {
        emailContextFacade.sendEmail(recipient, msgBody, subject, attachment);
    }
}
