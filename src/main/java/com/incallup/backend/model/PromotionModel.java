package com.incallup.backend.model;


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

public class PromotionModel {


    @Id
    @Column(name = "idPromotion")
    private Integer idPromotion;



    @Column(name = "promotion_amount")
    private String promotion_amount;
}
