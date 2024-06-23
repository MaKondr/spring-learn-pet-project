package com.example.finance_web_demo.services;

import com.example.finance_web_demo.models.Report;
import com.example.finance_web_demo.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReportService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
    @Transactional(readOnly = true)
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Report getReportById(long id) {
        return reportRepository.findReportById(id).orElse(null);
    }

    public List<Report> getReportsByProfileId(long id) {
        return reportRepository.findReportByProfileId(id);
    }
    public void saveReport(Report report) {
        reportRepository.save(report);
    }

    public void deleteReportById(long id) {
        reportRepository.deleteById(id);
    }

}
