package com.jay.emaildigest.service;

import com.jay.emaildigest.model.Notification;
import com.jay.emaildigest.repo.NotificationRepo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EmailService {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EmailService.class);

    @Value("${email.digest.email.from}")
    private String from;

    @Autowired
    private NotificationRepo notificationRepo;

    @Autowired
    private MailSender mailSender;

    //@Scheduled(cron = "0 0 * * * *") //Every hour of every day
    @Scheduled(cron = "0 * * * * *") // Every minute for testing
    public void sendHourlyDigest() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime hourBack = LocalDateTime.now().minusHours(1);

        LOG.info(String.format("Hourly schedular time now %s", now.toString()));

        List<Notification> notifications = notificationRepo.lastOneHourNotifications(hourBack, now);

        Map<String, String> notificationByEmail = notifications.stream()
                .collect(Collectors.groupingBy(Notification::getEmail,
                        Collectors.mapping(notification -> {
                            return String.format("%-20s%s",notification.getTimestamp().format(DateTimeFormatter.ofPattern("E, HH:mm")), notification.getMessage());
                        }, Collectors.joining("\n"))));

        notificationByEmail.entrySet()
                .stream()
                .forEach(emailEntry -> {
                    sendMailMessage(emailEntry.getKey(), emailEntry.getValue());
                });

    }

    private void sendMailMessage(String to, String body) {
        LOG.info(String.format("Sending email to %s, mesage %s", to, body));

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("Komoot hourly digest!");
        simpleMailMessage.setText(body);
        this.mailSender.send(simpleMailMessage);
    }

//    public static void main(String[] args) {
//        Notification n = new Notification();
//        n.setEmail("a@a.com");
//        n.setMessage("Hi");
//        n.setTimestamp(LocalDateTime.now());
//
//        Notification n1 = new Notification();
//        n1.setEmail("a@a.com");
//        n1.setMessage("bye");
//        n1.setTimestamp(LocalDateTime.now());
//
//        Notification n2 = new Notification();
//        n2.setEmail("b@a.com");
//        n2.setMessage("B Hi");
//        n2.setTimestamp(LocalDateTime.now());
//
//        List<Notification> notifications = Arrays.asList(n, n1, n2);
//
//        Map<String, String> collect = notifications.stream()
//                .collect(Collectors.groupingBy(Notification::getEmail,
//                            Collectors.mapping(notification -> {
//                                return String.format("%-20s%s",notification.getTimestamp().format(DateTimeFormatter.ofPattern("E, HH:mm")), notification.getMessage());
//                            }, Collectors.joining("\n"))));
//
//        System.out.println(collect);
//    }
}
