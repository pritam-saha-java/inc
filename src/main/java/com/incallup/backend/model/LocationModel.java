package com.incallup.backend.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LocationModel {

    @Id
    @Column(name = "idt_location")
    private Integer idt_location;

    @Column(name = "location_name")
    private String location_name;

}
