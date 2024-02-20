package com.incallup.backend.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbl_promotion")
public class Promotion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    private Integer id;

    @Column(name = "promotion_amount",nullable = false)
    private String amount;

    @CreationTimestamp
    @Column(name = "promotion_created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "promotion_updated_at")
    private Instant updatedAt;
}
