package com.incallup.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "tbl_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    public Integer id;

    @Column(name = "category_name",nullable = false,unique = true)
    public String name;

    @CreationTimestamp
    @Column(name = "category_created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "category_updated_at")
    private Instant updatedAt;
}
