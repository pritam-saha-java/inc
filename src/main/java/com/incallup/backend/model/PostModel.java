package com.incallup.backend.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    @Column(name = "post_location_id")
    private Integer post_location_id;

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

    @Column(name = "post_category_id ")
    private Integer post_category_id;

    @Column(name = "post_verification_id ")
    private Integer post_verification_id;

    @Column(name = "post_promotion_id ")
    private Integer post_promotion_id;
}
