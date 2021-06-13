package com.senla.model;

import lombok.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements AbstractEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_role")
    private UUID id;
    private RoleValue role;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "roles")
    private Set<User> users;

    public enum RoleValue {
        USER, ADMIN;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role=" + role +
                ", users=" + users +
                '}';
    }
}
