package com.incallup.backend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity(name = "tbl_admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer id;



    @NotNull
    @Column(name = "admin_username",unique = true,nullable = false)
    private String username;

    @NotNull
    @Column(name = "admin_password",nullable = false)
    private String password;

    @Column(name = "admin_type")
    private String type;



}
