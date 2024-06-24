package com.example.finance_web_demo.services;

import com.example.finance_web_demo.models.CreditCard;
import com.example.finance_web_demo.repository.CreditCardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CreditCardService {
    private final CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Transactional(readOnly = true)
    public List<CreditCard> findAll() {
        return creditCardRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<CreditCard> findByAccountId(long accountId) {
        return creditCardRepository.findByAccountId(accountId);
    }

    @Transactional(readOnly = true)
    public CreditCard findById(long id) {
        return creditCardRepository.findById(id).orElse(null);
    }

    public void save(CreditCard creditCard) {
        creditCardRepository.save(creditCard);
    }

    public void delete(long id) {
        creditCardRepository.delete(findById(id));
    }
}
