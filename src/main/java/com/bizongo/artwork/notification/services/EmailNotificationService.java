package com.bizongo.artwork.notification.services;

import com.bizongo.artwork.notification.beans.Notification;
import com.bizongo.artwork.notification.beans.NotificationRequest;
import com.bizongo.artwork.notification.beans.User;
import com.bizongo.artwork.notification.events.NotificationEvent;
import com.bizongo.artwork.notification.publishers.NotificationPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailNotificationService implements NotificationService{

    @Autowired
    NotificationPublisher notificationPublisher;

    @Override
    public void sendNotification(NotificationEvent event) {
        notificationPublisher.publishNotificationEvent(event);
    }

    public void resetPasswordNotification() {
        User user = new User(1, "Artwork User", User.Type.USER, "user1@artwork.com");
        List<User> users = new ArrayList<>();
        users.add(user);
        Notification notification = new Notification();
        notification.setReceivers(users);
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setEventName("artwork_reset_password");
        notificationRequest.setNotificationContent(notification);
        Map<String, String > payload = new HashMap<>();
        payload.put("user_name", "Artwork User");
        payload.put("company_name", "dasdadasd");
        payload.put("token_url", "sdfsdf/set_password?dhgf=fhng");
        notificationRequest.setEmailPayload(payload);
        NotificationEvent notificationEvent = new NotificationEvent("Ketan");
        notificationEvent.setNotificationRequest(notificationRequest);
        sendNotification(notificationEvent);
    }

    public void verifyEmailNotification() {
        User user = new User(1, "Artwork User", User.Type.USER, "user1@artwork.com");
        List<User> users = new ArrayList<>();
        users.add(user);
        Notification notification = new Notification();
        notification.setReceivers(users);
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setEventName("artwork_verify_email");
        notificationRequest.setNotificationContent(notification);
        Map<String, String > payload = new HashMap<>();
        payload.put("user_name", "Artwork User");
        payload.put("token_url", "sdfsdf/verify-email?dhgf=fhng");
        notificationRequest.setEmailPayload(payload);
        NotificationEvent notificationEvent = new NotificationEvent("Ketan");
        notificationEvent.setNotificationRequest(notificationRequest);
        sendNotification(notificationEvent);

    }

    public void welcomeNotification() {
        System.out.println("Send Notification service");
        User user = new User(1, "Artwork User", User.Type.USER, "user1@artwork.com");
        List<User> users = new ArrayList<>();
        users.add(user);
        Notification notification = new Notification();
        notification.setReceivers(users);
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setEventName("artwork_welcome");
        notificationRequest.setNotificationContent(notification);
        Map<String, String > payload = new HashMap<>();
        payload.put("user_name", "Artwork User");
        payload.put("login_url", "dasdadasd");
        payload.put("subject_notification_name", "sdfsdf");
        notificationRequest.setEmailPayload(payload);
        NotificationEvent notificationEvent = new NotificationEvent("Ketan");
        notificationEvent.setNotificationRequest(notificationRequest);
        sendNotification(notificationEvent);
    }
}
