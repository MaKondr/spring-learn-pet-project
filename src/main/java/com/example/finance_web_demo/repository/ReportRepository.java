package com.example.finance_web_demo.repository;

import com.example.finance_web_demo.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    public Optional<Report> findReportById(Long id);
}
