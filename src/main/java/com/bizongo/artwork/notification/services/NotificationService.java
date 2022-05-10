package com.bizongo.artwork.notification.services;

import com.bizongo.artwork.notification.events.NotificationEvent;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    void sendNotification(NotificationEvent event);
}
