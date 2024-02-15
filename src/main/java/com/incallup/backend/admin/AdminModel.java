package com.incallup.backend.admin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "tbl_admin")
@Data
@NoArgsConstructor
public class AdminModel {

    @Id
    private int idAdmin;

    @Column(name = "admin_username")
    private String username;

    @Column(name = "admin_password")
    private String password;

    @Column(name = "admin_type")
    private String type;



}
