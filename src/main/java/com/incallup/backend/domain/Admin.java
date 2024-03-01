package com.incallup.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbl_admin")
public class Admin
//        implements UserDetails
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer id;


//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "admin_role_junction",
//    joinColumns = {@JoinColumn(name = "admin_id")},
//    inverseJoinColumns = {@JoinColumn(name = "role_id")}
//    )
//    private Set<Role> authorities= new HashSet<>();

    @NotNull
    @Column(name = "admin_username", unique = true, nullable = false)
    private String username;

    @NotNull
    @JsonIgnore
    @Column(name = "admin_password", nullable = false)
    private String password;

    @Column(name = "admin_type")
    private String type;


    @CreationTimestamp
    @Column(name = "admin_created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "admin_updated_at")
    private Instant updatedAt;

    public static AdminBuilder builder() {
        return new AdminBuilder();
    }

    public static class AdminBuilder {
        private Integer id;
        private @NotNull String username;
        private @NotNull String password;
        private String type;
        private Instant createdAt;
        private Instant updatedAt;

        AdminBuilder() {
        }

        public AdminBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public AdminBuilder username(@NotNull String username) {
            this.username = username;
            return this;
        }

        @JsonIgnore
        public AdminBuilder password(@NotNull String password) {
            this.password = password;
            return this;
        }

        public AdminBuilder type(String type) {
            this.type = type;
            return this;
        }

        public AdminBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AdminBuilder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Admin build() {
            return new Admin(this.id, this.username, this.password, this.type, this.createdAt, this.updatedAt);
        }

        public String toString() {
            return "Admin.AdminBuilder(id=" + this.id + ", username=" + this.username + ", password=" + this.password + ", type=" + this.type + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.authorities;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
