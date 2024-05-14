package com.incallup.backend.domain;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "tbl_application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Integer id;

    @Column(name = "application_property",nullable = false,unique = true)
    private String name;

    @Column(name = "application_value",length = 10000)
    private String description;
}
