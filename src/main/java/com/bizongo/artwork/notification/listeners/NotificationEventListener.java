package com.bizongo.artwork.notification.listeners;

import com.bizongo.artwork.notification.beans.Notification;
import com.bizongo.artwork.notification.beans.NotificationRequest;
import com.bizongo.artwork.notification.beans.User;
import com.bizongo.artwork.notification.clients.NotificationClient;
import com.bizongo.artwork.notification.events.NotificationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NotificationEventListener{

    @Autowired
    NotificationClient notificationClient;

    @EventListener
    public void sendNotification(NotificationEvent event) {
        System.out.println("Notification Event listener sendNotification : " + event.getName());
        notificationClient.welcome(event.getNotificationRequest());
    }
}
