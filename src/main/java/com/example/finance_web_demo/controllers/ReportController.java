package com.example.finance_web_demo.controllers;

import com.example.finance_web_demo.services.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/profile/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public String listReports(@RequestParam("id") long id, Model model) {
        model.addAttribute("reports", reportService.getReportsByProfileId(id));
        return "profile/report/reports";

    }
}