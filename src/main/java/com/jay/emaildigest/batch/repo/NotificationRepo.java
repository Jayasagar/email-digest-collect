package com.jay.emaildigest.batch.repo;

import com.jay.emaildigest.batch.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepo extends MongoRepository<Notification, String> {

}
