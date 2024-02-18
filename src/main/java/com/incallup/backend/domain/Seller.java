package com.incallup.backend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "tbl_seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private int id;



    @Column(name = "seller_username",nullable = false,unique = true)
    private String username;

    @Email
    @Column(name = "seller_email",nullable = false)
    private String email;

    @Column(name = "seller_password",nullable = false)
    private String password;


    @Column(name = "seller_first_name",nullable = false)
    private String firstName;
    @Column(name = "seller_last_name",nullable = false)
    private String lastName;








}
