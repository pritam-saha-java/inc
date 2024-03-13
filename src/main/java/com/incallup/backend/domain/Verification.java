package com.incallup.backend.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_verification")
public class Verification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_id")
    private Integer id;

    @Column(name = "verification_is_verified")
    private Boolean isVerified;


    @CreationTimestamp
    @Column(name = "verification_created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "verification_updated_at")
    private Instant updatedAt;
}
