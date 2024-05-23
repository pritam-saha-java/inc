package com.incallup.backend.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "top_up_packages")
public class TopUpPackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "points")
    private Long points;

    @Column(name = "description")
    private String description;

}
