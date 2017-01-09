package com.jay.emaildigest.collect.repo;

import com.jay.emaildigest.collect.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepo extends MongoRepository<Notification, String> {

}
