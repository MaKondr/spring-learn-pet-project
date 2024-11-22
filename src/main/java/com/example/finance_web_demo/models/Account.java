package com.example.finance_web_demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {
    @Id
//    @ColumnDefault("nextval('accounts_id_seq'::regclass)")
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    @NotNull
    @Column(name = "account_number", nullable = false, length = 20)
    private String accountNumber = Long.toHexString((new Random()).nextLong());

    @NotNull
    @Column(name = "balance", nullable = false, precision = 15, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    @Size(max = 3)
    @NotNull
    @Column(name = "currency", nullable = false, length = 3)
    private String currency = "rub";

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();


    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name="account_id")
    private List<Contribution> contribution;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name="account_id")
    private List<CreditCard> creditCard;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name="account_id")
    private List<DebitCard> debitCard;



}