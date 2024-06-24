package com.example.finance_web_demo.services;

import com.example.finance_web_demo.models.DebitCard;
import com.example.finance_web_demo.repository.DebitCardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DebitCardService {
    private final DebitCardRepository debitCardRepository;

    public DebitCardService(DebitCardRepository debitCardRepository) {
        this.debitCardRepository = debitCardRepository;
    }

    @Transactional(readOnly = true)
    public List<DebitCard> getAllDebitCards() {
        return debitCardRepository.findAll();
    }

    @Transactional(readOnly = true)
    public DebitCard findById(long id) {
        return debitCardRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<DebitCard> findByAccountId(long accountId) {
        return debitCardRepository.findByAccountId(accountId);
    }

    public DebitCard saveDebitCard(DebitCard debitCard) {
        return debitCardRepository.save(debitCard);
    }

    public void deleteDebitCardById(long id) {
        debitCardRepository.delete(findById(id));
    }


}
