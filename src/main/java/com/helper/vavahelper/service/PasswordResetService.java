package com.helper.vavahelper.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.helper.vavahelper.models.User.PasswordResetToken;
import com.helper.vavahelper.models.User.User;
import com.helper.vavahelper.repositories.PasswordResetTokenRepository;
import com.helper.vavahelper.repositories.UserRepository;

@Service
public class PasswordResetService {
    @Autowired
    private PasswordResetTokenRepository tokenRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createPasswordResetToken(String email, String appUrl) {
        // busca via Spring Data – retorna um UserDetails, mas na verdade é um User
        UserDetails userDetails = userRepo.findByLogin(email);
        if (userDetails == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        // Faz o cast para a sua entidade User
        User user = (User) userDetails;

        // Remove tokens antigos e cria o novo...
        tokenRepo.deleteByExpiryDateBefore(LocalDateTime.now());

        // Cria novo token
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(1));
        tokenRepo.save(resetToken);

        // Envia e-mail com link de reset
        String resetLink = appUrl + "/auth/reset-password?token=" + token;
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getLogin());
        mail.setSubject("Recuperação de senha");
        mail.setText("Clique no link para redefinir sua senha:\n" + resetLink);
        mailSender.send(mail);
    }

    public void resetPassword(String token, String newPassword) {
        PasswordResetToken prt = tokenRepo.findByToken(token)
            .orElseThrow(() -> new IllegalArgumentException("Token inválido"));

        if (prt.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token expirado");
        }

        User user = prt.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
        
        tokenRepo.delete(prt);
    }
}
