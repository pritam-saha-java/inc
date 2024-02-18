package com.incallup.backend.domain;


import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "post_title", unique = true)
    private String post_title;

    @ToString.Exclude
    @ManyToOne(targetEntity = Location.class,fetch = FetchType.LAZY)
    private Location location;

    @Column(name = "post_views")
    private String post_views;

    @Column(name = "datetime")
    private String datetime;

    @Column(name = "post_contact")
    private String post_contact;

    @Column(name = "post_age")
    private Integer post_age;

    @Column(name = "post_description")
    private String post_description;

    @ManyToOne(targetEntity = Category.class)
    private Category category;

    @Column(name = "post_verification_id ")
    private Integer post_verification_id;


    @OneToMany(targetEntity = Promotion.class)
    @ToString.Exclude
    private Promotion post_promotion_id;
}
