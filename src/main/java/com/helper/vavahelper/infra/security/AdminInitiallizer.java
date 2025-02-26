package com.helper.vavahelper.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.helper.vavahelper.models.User.User;
import com.helper.vavahelper.models.User.UserRole;
import com.helper.vavahelper.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Configuration
public class AdminInitiallizer{

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitiallizer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    @Transactional
    public CommandLineRunner createAdminUser(){
        return args -> {
            if (userRepository.findByLogin("admin") == null) {
                User admin = new User();
                admin.setLogin("admin");
                admin.setPassword(passwordEncoder.encode("123"));
                admin.setRole(UserRole.ADMIN);
                userRepository.save(admin);
                System.out.println("User gerado!");
            }
        };
    }

}
