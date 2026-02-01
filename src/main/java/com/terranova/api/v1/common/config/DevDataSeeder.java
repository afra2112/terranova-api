package com.terranova.api.v1.common.config;

import com.terranova.api.v1.role.entity.Role;
import com.terranova.api.v1.role.enums.RoleEnum;
import com.terranova.api.v1.role.repository.RoleRepository;
import com.terranova.api.v1.user.entity.User;
import com.terranova.api.v1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("dev")
public class DevDataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByRoleName(RoleEnum.ROLE_BUYER).isEmpty() && roleRepository.findByRoleName(RoleEnum.ROLE_SELLER).isEmpty()){
            Role seller = new Role();
            seller.setRoleName(RoleEnum.ROLE_SELLER);

            Role buyer = new Role();
            buyer.setRoleName(RoleEnum.ROLE_BUYER);

            roleRepository.save(buyer);
            roleRepository.save(seller);
        }

        if(!userRepository.existsByEmailOrIdentification("admin@gmail.com", "1094247745")){
            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin1234"));
            admin.setRoles(roleRepository.findAll());
            admin.setNames("Andres Felipe");
            admin.setLastName("Ramirez");
            admin.setIdentification("1094247745");
            admin.setPhoneNumber("3102162732");
            admin.setBirthday(LocalDate.of(2007, 6, 5));
            admin.setRegisterDate(LocalDateTime.now());
            userRepository.save(admin);
        }
    }
}
