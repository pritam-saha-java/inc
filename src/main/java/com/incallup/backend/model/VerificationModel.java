package com.incallup.backend.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "tbl_verification")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class VerificationModel {

    @Id
    @Column(name = "idVerification")
    private Integer idVerification;

    @Column(name = "verification_type", unique = true)
    private String verification_type;
}
