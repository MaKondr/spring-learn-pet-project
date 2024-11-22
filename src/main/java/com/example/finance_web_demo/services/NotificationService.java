package com.example.finance_web_demo.services;

import com.example.finance_web_demo.models.Notification;
import com.example.finance_web_demo.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    public List<Notification> findAllNotifications() {
        return notificationRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Notification findNotificationById(long id) {
        return notificationRepository.findById(id).orElse(null);
    }
//    @Transactional(readOnly = true)
//    public List<Notification> findNotificationByProfileId(long id) {
//       return notificationRepository.findByProfileId(id);
//    }
    public void saveNotification(Notification notification) {
        notificationRepository.save(notification);
    }
    public void deleteNotification(Notification notification) {
        notificationRepository.delete(notification);
    }
}
