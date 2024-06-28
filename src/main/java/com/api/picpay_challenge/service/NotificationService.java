package com.api.picpay_challenge.service;

import com.api.picpay_challenge.client.AuthorizationClient;
import com.api.picpay_challenge.client.NotificationClient;
import com.api.picpay_challenge.entities.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationClient notificationClient;


    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public void sendNotifation(Transfer transfer) {
        try {
            logger.info("Sending notification...");

            var resp = notificationClient.sendNotication(transfer);

            if(resp.getStatusCode().isError()){
                logger.error("Error while sending notification, static code is not ok");
            }

        } catch (Exception e) {
            logger.error("Error while sending notification");
        }
    }
}
