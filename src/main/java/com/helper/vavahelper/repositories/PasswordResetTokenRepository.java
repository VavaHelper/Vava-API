package com.helper.vavahelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.helper.vavahelper.models.User.PasswordResetToken;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    // para buscar o token enviado por e-mail
    Optional<PasswordResetToken> findByToken(String token);

    // para limpar tokens expirados antes de agora
    void deleteByExpiryDateBefore(LocalDateTime now);
}
