package com.jay.emaildigest.batch;

import com.jay.emaildigest.batch.service.NotificationService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationSubject;
import org.springframework.cloud.aws.messaging.endpoint.NotificationStatus;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationMessageMapping;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationSubscriptionMapping;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationUnsubscribeConfirmationMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/sns/receive")
public class SNSNotificationController {

	@Autowired
	private NotificationService notificationService;

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SNSNotificationController.class);

	@RequestMapping("/greeting")
	public String greeting() {
		return "Hi Komoot";
	}

	@NotificationSubscriptionMapping
	public void handleSubscriptionMessage(NotificationStatus status) throws IOException {
		//We subscribe to start receive the message
		status.confirmSubscription();
		LOG.info("Subscription:",status);
	}

	@NotificationMessageMapping
	public void handleNotificationMessage(@NotificationSubject String subject, @NotificationMessage String message) {
		LOG.info(String.format("Message handle Subject: %s, Message: %s", subject, message));
		notificationService.saveNotification(message);
	}

	@NotificationUnsubscribeConfirmationMapping
	public void handleUnsubscribeMessage(NotificationStatus status) {
		//e.g. the client has been unsubscribed and we want to "re-subscribe"
		status.confirmSubscription();
	}
}