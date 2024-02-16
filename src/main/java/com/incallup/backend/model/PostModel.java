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


    post_title string
    post_location_id int
    post_views string
    post_date datetime
    post_contact string
    post_age int
    post_description string
    post_category_id int
    post_verification_id int
    post_promotion_id int
}
