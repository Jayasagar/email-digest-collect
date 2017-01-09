package com.jay.emaildigest.batch;

import com.jay.emaildigest.collect.JSON;
import com.jay.emaildigest.collect.model.Notification;
import org.junit.Assert;
import org.junit.Test;

public class JSONTest {

    @Test
    public void toObject_should_return_given_class_type() {
        // Arrange
        String message = "{\"timestamp\": \"2017-01-03T10:58:30\", \"message\": \"Bob sent you a message\", \"name\": \"Victor\", \"email\": \"x58d+Victor@komoot.de\"}";

        // Act
        Notification notification = JSON.MAPPER.toObject(message.getBytes(), Notification.class);

        // Assert
        Assert.assertEquals(notification.getEmail(), "x58d+Victor@komoot.de");
        Assert.assertEquals(notification.getName(), "Victor");
    }
}
