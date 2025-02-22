package com.helper.vavahelper.Models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Nome:
    @Column(unique = true, nullable = false)
    private String username;
    // Senha
    @Column(nullable = false)
    private String password;
    // Cargo 
    @Enumerated(EnumType.STRING)
    private Role role;
    // Data Criação
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    // Add Data Automático
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
