package com.firsht.smspublisher;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;


public class SMSPublisher implements RequestHandler<CloudWatchEvent, Void> {

    // Initializing clients outside handler so they can be reused across invocations

    private AmazonSNS snsClient = AmazonSNSClientBuilder.defaultClient();

    private static final String PHONE_NUMBER = "+15551234567";
    private static final String MESSAGE = "Hello!";


    public Void handleRequest(final SMSSendRequest request) {
        PublishRequest publishRequest = new PublishRequest()
                .withMessage(MESSAGE)
                .withPhoneNumber(PHONE_NUMBER);

        snsClient.publish(publishRequest);

        System.out.println("Sent message " + request.getMessage() + " to " + request.getPhoneNumber());

        return null;
    }
}