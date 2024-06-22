package com.example.finance_web_demo.controllers;

import com.example.finance_web_demo.models.Account;
import com.example.finance_web_demo.services.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //    @GetMapping("/create")
//    public String createAccount(Model model){
//        model.addAttribute("account", new Account());
//        return "account/profile";
//    }
    @GetMapping("/create")
    public String createAccount() {
//        accountService.createAccount();
        System.out.println("ACCESS");
        return "redirect:/account/";

    }

    @GetMapping("/{id}")
    public String account(@PathVariable Long id, Model model) {
        model.addAttribute("account", accountService.getAccountById(id));

        return "account/account";
    }
}
