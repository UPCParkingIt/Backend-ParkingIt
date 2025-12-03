package com.parkingit.notifications.interfaces.acl;

public interface EmailContextFacade {
    String sendEmail(String recipient, String msgBody, String subject, String attachment);
}
