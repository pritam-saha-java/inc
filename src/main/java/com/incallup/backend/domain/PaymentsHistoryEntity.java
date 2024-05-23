package com.incallup.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "payment_history")
@Data
@RequiredArgsConstructor
public class PaymentsHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private LocalDate paymentDate;
    private String userId;
    private Long amount;
    private String upiId;
    private String transactionId;
}
