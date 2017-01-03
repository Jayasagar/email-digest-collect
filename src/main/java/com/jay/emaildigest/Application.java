package com.jay.emaildigest;

import com.jay.emaildigest.model.Notification;
import com.jay.emaildigest.repo.NotificationRepo;
import com.jay.emaildigest.service.EmailService;
import com.jay.emaildigest.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.mail.MailSender;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
@ImportResource("classpath:aws-sns.xml")
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Application.class).web(true).run(args);

//        Notification n = new Notification();
//        n.setEmail("a@a.com");
//        n.setMessage("Hi4");
//        n.setTimestamp(LocalDateTime.now());
//
        //NotificationRepo notificationRepo = ctx.getBean(NotificationRepo.class);
        //notificationRepo.save(n);
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
}
