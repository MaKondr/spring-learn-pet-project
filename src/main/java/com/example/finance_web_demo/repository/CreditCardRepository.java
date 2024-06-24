package com.example.finance_web_demo.repository;

import com.example.finance_web_demo.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    public List<CreditCard> findByAccountId(long accountId);
}
