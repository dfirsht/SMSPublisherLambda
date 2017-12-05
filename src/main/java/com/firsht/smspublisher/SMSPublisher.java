package com.firsht.smspublisher;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sns.model.SubscribeRequest;


public class SMSPublisher implements RequestHandler<CloudWatchEvent, Void> {

    // Initializing clients outside handler so they can be reused across invocations

    private AmazonSNS snsClient = AmazonSNSClientBuilder.defaultClient();

    private static final String PHONE_NUMBER = "+15551234567";
    private static final String MESSAGE = "Hello!";


    public Void handleRequest(final CloudWatchEvent request, Context context) {
        CreateTopicRequest createTopicRequest = new CreateTopicRequest()
                .withName("NumberTopic");
        CreateTopicResult result = snsClient.createTopic(createTopicRequest);

        SubscribeRequest subscribeRequest = new SubscribeRequest()
                .withTopicArn(result.getTopicArn())
                .withProtocol("sms")
                .withEndpoint(PHONE_NUMBER);
        snsClient.subscribe(subscribeRequest);

        PublishRequest publishRequest = new PublishRequest()
                .withMessage(MESSAGE)
                .withTopicArn(result.getTopicArn());
        snsClient.publish(publishRequest);

        System.out.println("Sent message " + MESSAGE + " to " + PHONE_NUMBER);

        return null;
    }
}