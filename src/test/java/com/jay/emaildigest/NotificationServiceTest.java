package com.jay.emaildigest;

import com.jay.emaildigest.model.Notification;
import com.jay.emaildigest.model.User;
import com.jay.emaildigest.repo.NotificationRepo;
import com.jay.emaildigest.service.NotificationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;
    @Mock
    private NotificationRepo notificationRepo;

    @Test
    public void user_with_hourly_digest_body_message_joining_works() {

        // Arrange
        LocalDateTime localDateTime = LocalDateTime.now();
        Notification notification = new Notification();
        notification.setEmail("jayasagar@gmail.com");
        notification.setTimestamp(localDateTime);
        notification.setMessage("Message 1");

        Mockito.when(notificationRepo.lastOneHourNotifications(Mockito.anyObject(), Mockito.anyObject())).thenReturn(Arrays.asList(notification));

        // Act
        Map<User, String> userHourlyDigestBody = notificationService.lastOneHourNotifications();

        // Assert
        User user = User.builder().email("jayasagar@gmail.com").build();
        String dayTime = localDateTime.format(DateTimeFormatter.ofPattern("EEEE, HH:mm"));

        Assert.assertEquals(userHourlyDigestBody.get(user), String.format("%-35s%s", dayTime, "Message 1"));
    }
}

