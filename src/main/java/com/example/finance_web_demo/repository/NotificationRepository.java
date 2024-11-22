package com.example.finance_web_demo.repository;

import com.example.finance_web_demo.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    public Optional<Notification> findById(long id);
}

