package com.psycho.backend.domain.user;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    private String firstname;
    private String lastname;

    private String email;
    private String phone;

    @Column(name = "account_non_expired")
    private Boolean isAccountNonExpired;
    @Column(name = "non_locked")
    private Boolean isAccountNonLocked;
    @Column(name = "credentials_non_expired")
    private Boolean isCredentialsNonExpired;
    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles")
    @Enumerated(value = EnumType.STRING)
    private Set<Role> roles;
}
