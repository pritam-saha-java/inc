package com.incallup.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity(name = "tbl_admin")
public class Admin implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer id;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "admin_role_junction",
    joinColumns = {@JoinColumn(name = "admin_id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> authorities= new HashSet<>();

    @NotNull
    @Column(name = "admin_username",unique = true,nullable = false)
    private String username;

    @NotNull
    @JsonIgnore
    @Column(name = "admin_password",nullable = false)
    private String password;

    @Column(name = "admin_type")
    private String type;


    @CreationTimestamp
    @Column(name = "admin_created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "admin_updated_at")
    private Instant updatedAt;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
