package com.incallup.backend.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "tbl_post")
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer id;

    @Max(15)
    @Column(name = "post_title", unique = true)
    private String title;

    @ToString.Exclude
    @ManyToOne
    private Location location;

    @Column(name = "post_views")
    private Integer views;



    @Column(name = "post_contact")
    private String contact;

    @Column(name = "post_age")
    private Integer age;

    @Max(50)
    @Column(name = "post_description")
    private String description;

    @ManyToOne
    private Category category;




    @OneToOne
    @ToString.Exclude
    private Promotion promotion;



    @CreationTimestamp
    @Column(name = "post_created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "post_updated_at")
    private Instant updatedAt;
}
