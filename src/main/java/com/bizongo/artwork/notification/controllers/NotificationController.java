package com.bizongo.artwork.notification.controllers;


import com.bizongo.artwork.notification.services.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    EmailNotificationService emailNotificationService;

    @PostMapping(value = "/welcome")
    public String welcome(){
        emailNotificationService.welcomeNotification();
        return "Mail sent successfully";
    }

    @PostMapping(value = "/reset")
    public String resetPassword(){
        emailNotificationService.resetPasswordNotification();
        return "Reset Mail sent successfully";
    }

    @PostMapping(value = "/verify")
    public String verifyEmail(){
        emailNotificationService.verifyEmailNotification();
        return "Verify Mail sent successfully";
    }
}
