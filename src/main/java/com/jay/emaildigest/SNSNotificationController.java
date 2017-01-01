package com.jay.emaildigest;

import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationSubject;
import org.springframework.cloud.aws.messaging.endpoint.NotificationStatus;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationMessageMapping;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationSubscriptionMapping;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationUnsubscribeConfirmationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/arn:aws:sns:us-west-2:292312626325:emaildigest")
public class SNSNotificationController {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SNSNotificationController.class);

	@RequestMapping("/greeting")
	public String greeting() {
		return "HI";
	}

	@NotificationSubscriptionMapping
	public void handleSubscriptionMessage(NotificationStatus status) throws IOException {
		//We subscribe to start receive the message
		status.confirmSubscription();
		System.out.println("Test Subscription:");
		LOG.info("Test Subscription:",status);
	}

	@NotificationMessageMapping
	public void handleNotificationMessage(@NotificationSubject String subject, @NotificationMessage String message) {
		// ...
		System.out.println("Test :" + subject + message);
		LOG.info("Test handle:", subject, message);
	}

	@NotificationUnsubscribeConfirmationMapping
	public void handleUnsubscribeMessage(NotificationStatus status) {
		//e.g. the client has been unsubscribed and we want to "re-subscribe"
		status.confirmSubscription();
	}
}