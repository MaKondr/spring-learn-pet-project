package com.example.finance_web_demo.controllers;

import com.example.finance_web_demo.services.DebitCardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account/debit_card")
public class DebitCardController {
    private final DebitCardService debitCardService;

    public DebitCardController(DebitCardService debitCardService) {
        this.debitCardService = debitCardService;
    }

    @GetMapping
    public String debitCard(@RequestParam("id") long id, Model model) {
        model.addAttribute("debitCards", debitCardService.findByAccountId(id));
        return "profile/account/cards/debit_card";
    }
}
