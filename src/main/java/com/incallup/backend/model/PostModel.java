package com.incallup.backend.model;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "tbl_post")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostModel {


    @Id
    @Column(name = "idpost")
    private Integer idpost;

    @Column(name = "post_title", unique = true)
    private String post_title;

    @ToString.Exclude
    @ManyToOne(targetEntity = LocationModel.class,fetch = FetchType.LAZY)
    private LocationModel location;

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

    @ManyToOne(targetEntity = CategoryModel.class)
    private CategoryModel category;

    @Column(name = "post_verification_id ")
    private Integer post_verification_id;
    //remove later

    @OneToMany(targetEntity = PromotionModel.class)
    @ToString.Exclude
    private PromotionModel post_promotion_id;
}
