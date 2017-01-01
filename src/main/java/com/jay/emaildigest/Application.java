package com.jay.emaildigest;

import com.jay.emaildigest.model.Notification;
import com.jay.emaildigest.repo.NotificationRepo;
import com.jay.emaildigest.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Application.class).web(true).run(args);

//        Notification n = new Notification();
//        n.setEmail("a@a.com");
//        n.setMessage("Hi4");
//        n.setTimestamp(LocalDateTime.now());
//
//        NotificationRepo notificationRepo = ctx.getBean(NotificationRepo.class);
//        notificationRepo.save(n);
//
//        List<Notification> notifications = notificationRepo.lastOneHourNotifications(LocalDateTime.now().minusMinutes(60), LocalDateTime.now());
//        System.out.println(notifications);

        //EmailService emailService = ctx.getBean(EmailService.class);
       // emailService.sendMailMessage("jayasagar@gmail.com", "Test email", "Hi Test email");
    }
}
