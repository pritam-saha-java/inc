package com.incallup.backend.domain;

import jakarta.persistence.*;
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
    @Column(name = "id_admin")
    private Integer adminId;



    @Column(name = "admin_username",unique = true)
    private String username;

    @Column(name = "admin_password")
    private String password;

    @Column(name = "admin_type")
    private String type;



}
