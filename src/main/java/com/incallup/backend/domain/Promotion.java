package com.incallup.backend.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "tbl_promotion")
@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Promotion {


    @Id
    @Column(name = "idPromotion")
    private Integer promotionId;



    @Column(name = "promotion_amount")
    private String promotion_amount;
}
