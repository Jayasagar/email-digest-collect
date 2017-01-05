package com.jay.emaildigest.batch.repo;

import com.jay.emaildigest.batch.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepo extends MongoRepository<Notification, String> {

    @Query("{ 'timestamp' : { $gt: ?0, $lt: ?1 } }")
    List<Notification> lastOneHourNotifications(LocalDateTime from, LocalDateTime to);
}
