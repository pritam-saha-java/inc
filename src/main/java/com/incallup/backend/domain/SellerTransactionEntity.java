package com.incallup.backend.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "seller_transaction")
@Data
public class SellerTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private LocalDate createdAt;

    private Long topUpPackageId;

    private String transactionId;
}
