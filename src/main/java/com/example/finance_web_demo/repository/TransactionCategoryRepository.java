package com.example.finance_web_demo.repository;

import com.example.finance_web_demo.models.TransactionCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionCategoryRepository extends JpaRepository<TransactionCategory, Long> {}
