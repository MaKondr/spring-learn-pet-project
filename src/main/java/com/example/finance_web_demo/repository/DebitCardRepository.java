package com.example.finance_web_demo.repository;

import com.example.finance_web_demo.models.DebitCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebitCardRepository extends JpaRepository<DebitCard, Long> {
    public List<DebitCard> findByAccountId(long id);
}

