package com.incallup.backend.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "tbl_location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Integer id;

    @Column(name = "location_name",nullable = false)
    private String name;
    @Column(name = "location_district",unique = true,nullable = false)
    private String district;

    @Column(name = "location_state",nullable = false)
    private String state;

    @CreationTimestamp
    @Column(name = "location_created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "location_updated_at")
    private Instant updatedAt;


    @Lob
    @Column(name = "location_description",length = 1000)
    private String description;

    @Lob
    @Column(name = "location_meta",length = 1000)
    private String meta;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

}
