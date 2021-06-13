package com.senla.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails, AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private UUID uuid;
    @Column(name = "username")
    private String username;
    @Column(name = "login")
    private String login;
    private String Password;

    @ManyToMany( fetch = FetchType.LAZY,
             cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "id_user"),
                inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }



    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public UUID getId() {
        return this.uuid;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", username='" + username + '\'' +
                ", login='" + login + '\'' +
                ", Password='" + Password;
    }
}
