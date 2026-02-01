package com.terranova.api.v1.role.entity;

import com.terranova.api.v1.role.enums.RoleEnum;
import com.terranova.api.v1.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "roles")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role(RoleEnum roleEnum) {
    }
}
