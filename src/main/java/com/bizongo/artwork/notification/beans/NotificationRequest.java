package com.bizongo.artwork.notification.beans;

import java.util.List;

public class NotificationRequest {


    private Notification notificationContent;
    private String eventName;
    public Object emailPayload;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Notification getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(Notification notificationContent) {
        this.notificationContent = notificationContent;
    }

    public Object getEmailPayload() {
        return emailPayload;
    }

    public void setEmailPayload(Object emailPayload) {
        this.emailPayload = emailPayload;
    }
}
