package com.api.picpay_challenge.client;

import com.api.picpay_challenge.entities.Transfer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NotificationClient", url = "${client.notification-service-url}")
public interface NotificationClient {

    @PostMapping
    ResponseEntity<Void> sendNotication(@RequestBody Transfer transfer);
}
