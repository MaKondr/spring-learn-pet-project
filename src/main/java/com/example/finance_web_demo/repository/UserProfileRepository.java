package com.example.finance_web_demo.repository;

import com.example.finance_web_demo.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {}
