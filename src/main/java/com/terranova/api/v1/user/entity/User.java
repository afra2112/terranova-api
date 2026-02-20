package com.terranova.api.v1.user.entity;

import com.terranova.api.v1.auth.infrastructure.adapter.out.mysql.entity.RefreshTokenEntity;
import com.terranova.api.v1.role.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private UUID userId;

    @Column(length = 10, unique = true)
    private String identification;

    @Column(length = 45, nullable = false)
    private String names;

    @Column(length = 45, nullable = false)
    private String lastName;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 10, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDate birthday;

    private LocalDateTime registerDate;

    private String profilePicture;

//    private boolean notificacionesDisponibilidades = true;
//
//    private boolean notificacionesCitas = true;
//
//    private boolean notificacionesVentas = true;
//
//    private boolean notificacionesProductos = true;
//
//    private boolean notificacionesSistema = true;
//
//    private boolean recibirCorreos = true;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
//
//    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Favorito> favoritos;
//
//    @OneToMany(mappedBy = "vendedor")
//    private List<Producto> disponibilidad;
//
//    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Asistencia> asistencias;

    private int userScore;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RefreshTokenEntity> refreshTokenEntities;
}
