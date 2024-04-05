package com.incallup.backend.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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


    @Column(name = "post_title",length = 1000)
    private String title;


    @Column(name = "post_name",length = 1000)
    private String name;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Location location;

    @Column(name = "post_views")
    private Integer views;

    @Column(name = "post_telegram")
    private Boolean telegram;

    @Column(name = "post_whatsapp")
    private Boolean whatsapp;



    @Column(name = "post_contact")
    private String contact;

    @Column(name = "post_age")
    private Integer age;

//    @Max(50)
    @Column(name = "post_description",length = 10000)
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @ToString.Exclude
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

    @Lob
    @Column(length = 100000)
    private byte[] imageData1;
    @Lob
    @Column(length = 100000)
    private byte[] imageData2;

    @Transient
    private String byteString;
    @Transient
    private String byteString2;

    @Transient
    private String date;

    @Column(name = "post_is_blocked")
    private Boolean isBlocked  = false;


}
