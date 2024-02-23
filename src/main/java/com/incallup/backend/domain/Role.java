package com.incallup.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;

@Getter
@Setter
@Entity(name = "tbl_role")
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;
    @CreationTimestamp
    @Column(name = "role_created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "role_updated_at")
    private Instant updatedAt;

    @Column(name = "role_authority")
    private String authority;
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
