package com.bizongo.artwork.notification.publishers;

import com.bizongo.artwork.notification.beans.Notification;
import com.bizongo.artwork.notification.beans.NotificationRequest;
import com.bizongo.artwork.notification.beans.User;
import com.bizongo.artwork.notification.events.NotificationEvent;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NotificationPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishNotificationEvent(NotificationEvent notificationEvent) {
        System.out.println("Publishing Notification Event");
        applicationEventPublisher.publishEvent(notificationEvent);
    }
}
