package com.jay.emaildigest.service;

import com.jay.emaildigest.JSON;
import com.jay.emaildigest.model.Notification;
import com.jay.emaildigest.repo.NotificationRepo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private NotificationRepo notificationRepo;

    public void saveNotification(String message) {
        Notification notification = JSON.MAPPER.toObject(message.getBytes(), Notification.class);
        notificationRepo.save(notification);
    }
}
