package com.incallup.backend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Entity(name = "tbl_seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private int id;

//    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
//    @MapsId
//    @JoinColumn(name = "verification_id")
//    private Verification verification ;

    @Column(name = "seller_username",nullable = false,unique = true)
    private String username;

    @Column(name = "seller_incall",nullable = false)
    private String incall;

    @Column(name = "seller_outcall",nullable = false)
    private String outcall;

    @Email
    @Column(name = "seller_email",nullable = false)
    private String email;

    @Column(name = "seller_password",nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "seller_created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "seller_updated_at")
    private Instant updatedAt;

    @ToString.Exclude
    @OneToMany(orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private List<Post> posts = new ArrayList<>();

    @Column(name = "seller_is_blocked")
    private Boolean isBlocked  = false;

}
