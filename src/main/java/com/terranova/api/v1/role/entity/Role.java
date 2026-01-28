package com.terranova.api.v1.role.entity;

import com.terranova.api.v1.role.enums.RoleEnum;
import com.terranova.api.v1.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "role")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RoleId;

    @Enumerated(EnumType.STRING)
    private RoleEnum RolName;

    @ManyToMany(mappedBy = "roles")
    private List<User> Users;
}
