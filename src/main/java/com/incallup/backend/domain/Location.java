package com.incallup.backend.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "tbl_location")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Location {

    @Id
    @Column(name = "idt_location")
    private Integer locationId;

    @Column(name = "location_name")
    private String location_name;

}
