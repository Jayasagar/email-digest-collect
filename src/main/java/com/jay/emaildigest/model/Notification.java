package com.jay.emaildigest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

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
}
