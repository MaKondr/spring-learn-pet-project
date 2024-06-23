package com.example.finance_web_demo.controllers;

import com.example.finance_web_demo.services.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profile/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    @GetMapping
    public String listNotifications(@RequestParam("id") long id, Model model) {
        model.addAttribute("notifications" ,notificationService.findNotificationByProfileId(id));
        return "profile/notification/notifications";
    }
}
