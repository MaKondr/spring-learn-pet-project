package com.example.finance_web_demo.repository;

import com.example.finance_web_demo.models.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}
