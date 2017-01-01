package com.jay.emaildigest.repo;

import com.jay.emaildigest.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepo extends MongoRepository<Notification, String> {
}
