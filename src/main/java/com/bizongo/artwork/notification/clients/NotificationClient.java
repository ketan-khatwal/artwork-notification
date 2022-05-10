package com.bizongo.artwork.notification.clients;

import com.bizongo.artwork.notification.beans.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Notification", url="http://localhost:7000")
public interface NotificationClient {
    @PostMapping("/notification")
    Object welcome(@RequestBody NotificationRequest notificationRequest);
}
