package com.jay.emaildigest;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.core.env.ResourceIdResolver;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.endpoint.NotificationMessageHandlerMethodArgumentResolver;
import org.springframework.cloud.aws.messaging.endpoint.NotificationStatusHandlerMethodArgumentResolver;
import org.springframework.cloud.aws.messaging.endpoint.NotificationSubjectHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.PollableChannel;

@Configuration
public class AppConfiguration {

    @Bean
    public AmazonSNS amazonSNS() {
        return new AmazonSNSClient();
    }

    @Bean
    public NotificationStatusHandlerMethodArgumentResolver notificationResolver() {
        return new NotificationStatusHandlerMethodArgumentResolver(amazonSNS());
    }

    @Bean
    public NotificationMessageHandlerMethodArgumentResolver notificationMessageHandlerMethodArgumentResolver() {
        return new NotificationMessageHandlerMethodArgumentResolver();
    }

    @Bean
    public NotificationSubjectHandlerMethodArgumentResolver notificationSubjectHandlerMethodArgumentResolver() {
        return new NotificationSubjectHandlerMethodArgumentResolver();
    }
}
