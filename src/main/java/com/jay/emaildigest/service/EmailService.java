package com.jay.emaildigest.service;

import com.jay.emaildigest.model.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Component
public class EmailService {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EmailService.class);

    @Value("${email.digest.email.from}")
    private String from;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private MailSender mailSender;

    //@Scheduled(cron = "0 0 * * * *") //Every hour of every day
    @Scheduled(cron = "0 * * * * *") // Every minute for testing
    public void sendHourlyDigestEmail() {
        LOG.info(String.format("Hourly schedular, time now: %s", LocalDateTime.now().toString()));

        Map<User, String> userHourlyDigestBody = notificationService.lastOneHourNotifications();

        userHourlyDigestBody
                .entrySet()
                .stream()
                .forEach(entry -> {
                    StringBuilder body = new StringBuilder()
                            .append(String.format("Hi %s, your friends are active!", entry.getKey().getName()))
                            .append("\n\n")
                            .append(entry.getValue());

                    sendMailMessage(entry.getKey().getEmail(), body.toString());
                });
    }

    private void sendMailMessage(String to, String body) {
        LOG.info(String.format("Sending email to %s, mesage %s and from: %s", to, body, from));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Komoot hourly digest!");

        message.setText(body);

        this.mailSender.send(message);
    }
}
