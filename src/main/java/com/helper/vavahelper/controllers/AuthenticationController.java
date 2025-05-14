package com.helper.vavahelper.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helper.vavahelper.infra.security.TokenService;
import com.helper.vavahelper.models.User.User;
import com.helper.vavahelper.models.User.UserRole;
import com.helper.vavahelper.models.User.body.AuthenticationDTO;
import com.helper.vavahelper.models.User.body.LoginResponseDTO;
import com.helper.vavahelper.models.User.body.UserRegisterDTO;
import com.helper.vavahelper.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;


@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired 
    private UserRepository repository;

    @PostMapping("/register")
    public ResponseEntity<String> postMethodRegister(@RequestBody @Valid UserRegisterDTO data){

        if(repository.findByLogin(data.login()) != null) return ResponseEntity
        .badRequest().body("Username already taken.");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserRole role = UserRole.USER;

        User newUser = new User(data.login(), encryptedPassword, role);
        this.repository.save(newUser);
        return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> postMethodLogin(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User)auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}