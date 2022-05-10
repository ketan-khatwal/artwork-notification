package com.bizongo.artwork.notification.events;

import com.bizongo.artwork.notification.beans.NotificationRequest;

public class NotificationEvent {
    private String name;
    private NotificationRequest notificationRequest;

    public NotificationEvent() {}

    public NotificationEvent(String name) {
        this.name = name;
    }

    public String sendNotification(){

        return "Notification sent";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NotificationRequest getNotificationRequest() {
        return notificationRequest;
    }

    public void setNotificationRequest(NotificationRequest notificationRequest) {
        this.notificationRequest = notificationRequest;
    }
}
