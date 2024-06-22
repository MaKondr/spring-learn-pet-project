package com.example.finance_web_demo.services;

import com.example.finance_web_demo.models.Account;
import com.example.finance_web_demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

@Service
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Transactional(readOnly = true)
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Account getAccountById(long id) {
        return accountRepository.findById(id).orElseThrow(RuntimeException::new);
    }
//    public void createAccount() {
//        Account account = new Account();
//        enrichAccount(account);
//        accountRepository.save(account);
//    }

//    private void enrichAccount(Account account) {
//        account.setAccountNumber(Long.toString(new Random().nextLong()));
//        account.setCreatedAt(Instant.now());
//        account.setBalance(BigDecimal.valueOf(0));
//        account.setCurrency("rub");
//    }
}
