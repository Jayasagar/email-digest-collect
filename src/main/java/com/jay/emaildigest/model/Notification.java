package com.jay.emaildigest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.AbstractMap;

@Document
@Getter @Setter @ToString(of = {"email", "name", "timestamp", "message"})
@EqualsAndHashCode(of = "email")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Notification {
    @Id
    private String id;
    private String email;
    private String name;
    private String message;
    private LocalDateTime timestamp;

    public AbstractMap.SimpleImmutableEntry<User, Notification> userNotificationEntry() {
        return new AbstractMap.SimpleImmutableEntry<User, Notification>(
                User.builder()
                        .email(email)
                        .name(name)
                        .build(), this);
    }
}
