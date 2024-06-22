package com.example.finance_web_demo.repository;

import com.example.finance_web_demo.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long> {
}
