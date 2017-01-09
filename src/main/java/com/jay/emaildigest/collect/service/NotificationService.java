package com.jay.emaildigest.collect.service;

import com.jay.emaildigest.collect.JSON;
import com.jay.emaildigest.collect.model.Notification;
import com.jay.emaildigest.collect.repo.NotificationRepo;
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

        LOG.info(String.format("Notification saved %s,%s", notification.getEmail(), notification.getName()));
    }
}
