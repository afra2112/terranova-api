package com.terranova.api.v1.user.infrastructure.adapter.out.persistence.entity;

import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.entity.enums.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

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

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @NotEmpty
    private List<RoleEnum> roles;
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

}
