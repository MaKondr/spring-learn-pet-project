package com.example.finance_web_demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "debit_card")
public class DebitCard {

        @Id
//    @ColumnDefault("nextval('credit_cards_id_seq'::regclass)")
        @Column(name = "id", nullable = false)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Size(max = 16)
        @NotNull
        @Column(name = "card_number", nullable = false, length = 16)
        private String cardNumber;

        @NotNull
        @Column(name = "expiry_date", nullable = false)
        private LocalDate expiryDate;

        @Size(max = 3)
        @NotNull
        @Column(name = "cvv", nullable = false, length = 3)
        private String cvv;

        @Size(max = 100)
        @NotNull
        @Column(name = "name_on_card", nullable = false, length = 100)
        private String nameOnCard;

        @ManyToOne(fetch = FetchType.LAZY)
        @OnDelete(action = OnDeleteAction.SET_NULL)
        @JoinColumn(name = "account_id")
        private Account account;

    }
