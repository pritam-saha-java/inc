package com.incallup.backend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@Entity(name = "tbl_admin")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Admin {

    @Id
    @Column(name = "admin_id")
    private Integer adminId;



    @NotNull
    @Column(name = "admin_username",unique = true,nullable = false)
    private String username;

    @NotNull
    @Column(name = "admin_password",nullable = false)
    private String password;

    @Column(name = "admin_type")
    private String type;



}
