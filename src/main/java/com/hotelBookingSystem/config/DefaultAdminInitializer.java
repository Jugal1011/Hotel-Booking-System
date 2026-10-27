package com.hotelBookingSystem.config;

import com.hotelBookingSystem.entity.User;
import com.hotelBookingSystem.enums.Role;
import com.hotelBookingSystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
public class DefaultAdminInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String email;
    private final String password;

    public DefaultAdminInitializer(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            @Value("${admin.email:}") String email,
            @Value("${admin.password:}") String password) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.email = email;
        this.password = password;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (email.isBlank() || password.isBlank()) {
            log.warn("Default hotel manager was not created; configure ADMIN_EMAIL and ADMIN_PASSWORD.");
            return;
        }

        if (userRepository.findByEmail(email).isPresent()) {
            log.info("Default hotel manager already exists; skipping creation.");
            return;
        }

        User admin = new User();
        admin.setEmail(email);
        admin.setName("Hotel Manager");
        admin.setPassword(passwordEncoder.encode(password));
        admin.setRoles(Set.of(Role.HOTEL_MANAGER));

        userRepository.save(admin);
        log.info("Created default hotel manager account.");
    }
}
