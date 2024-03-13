package com.incallup.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "tbl_payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer id;

    @Column(name = "payment_amount")
    private String amount;
    @Column(name = "payment_upi_id")
    private String upiId;
    @Column(name = "payment_transaction_id")
    private String transactionId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "seller_id")
    @ToString.Exclude
    private Seller seller;

    @CreationTimestamp
    @Column(name = "payment_created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "payment_updated_at")
    private Instant updatedAt;


}
