package com.example.finance_web_demo.controllers;

import com.example.finance_web_demo.services.CreditCardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account/credit_card")
public class CreditCardController {
    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping
    public String getCreditCard(@RequestParam("id") long id, Model model) {
        model.addAttribute("creditCards", creditCardService.findByAccountId(id));
        return "profile/account/cards/credit_card";
    }
}
