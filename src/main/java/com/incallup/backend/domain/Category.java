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
@EqualsAndHashCode
@Entity(name = "tbl_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;

    @Column(name = "category_name",nullable = false,unique = true)
    private String name;

    @Column(name = "category_title",nullable = false,unique = true)
    private String title;

    @CreationTimestamp
    @Column(name = "category_created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "category_updated_at")
    private Instant updatedAt;

    @Column(name = "category_description")
    private String description;

    @Column(name = "category_meta")
    private String meta;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
}
