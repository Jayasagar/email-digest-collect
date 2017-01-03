package com.jay.emaildigest;

import com.jay.emaildigest.model.Notification;
import com.jay.emaildigest.repo.NotificationRepo;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableAutoConfiguration
@ImportResource("classpath:aws-sns.xml")
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Application.class).web(true).run(args);

        // Save few record for basic test already!
        saveRecords(ctx);
//
//        List<Notification> notifications = notificationRepo.lastOneHourNotifications(LocalDateTime.now().minusMinutes(60), LocalDateTime.now());
//        System.out.println(notifications);

        //EmailService emailService = ctx.getBean(EmailService.class);
       // emailService.sendMailMessage("jayasagar@gmail.com", "Test email", "Hi Test email");

//        String message = "{\"timestamp\": \"2017-01-03T10:58:30\", \"message\": \"Bob sent you a message\", \"name\": \"Victor\", \"email\": \"x58d+Victor@komoot.de\"}";
//        NotificationService notificationService = ctx.getBean(NotificationService.class);
//
//        notificationService.saveNotification(message);
//
//        List<Notification> notifications = notificationRepo.findAll();
//        System.out.println(notifications);
    }

    private static void saveRecords(ConfigurableApplicationContext ctx) {
        NotificationRepo notificationRepo = ctx.getBean(NotificationRepo.class);
        Notification notification = new Notification();
        notification.setEmail("jayasagar@gmail.com");
        notification.setTimestamp(LocalDateTime.now());
        notification.setMessage("Date now cron test 1");
        notificationRepo.save(notification);

        notification = new Notification();
        notification.setEmail("jayasagar@gmail.com");
        notification.setTimestamp(LocalDateTime.now().minusHours(1));
        notification.setMessage("Date now cron test 1");
        notificationRepo.save(notification);

        notification = new Notification();
        notification.setEmail("jayasagar@gmail.com");
        notification.setTimestamp(LocalDateTime.now().minusHours(2));
        notification.setMessage("Date now cron test 1");
        notificationRepo.save(notification);

    }
}
