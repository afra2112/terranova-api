package com.terranova.api.v1.role.repository;

import com.terranova.api.v1.role.entity.Role;
import com.terranova.api.v1.role.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(RoleEnum name);
}
