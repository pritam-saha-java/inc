package com.incallup.backend.domain;


import jakarta.persistence.*;
import lombok.*;

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
}
