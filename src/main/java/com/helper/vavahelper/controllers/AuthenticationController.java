package com.helper.vavahelper.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.helper.vavahelper.models.User.User;
import com.helper.vavahelper.models.User.UserRole;
import com.helper.vavahelper.models.User.body.AuthenticationDTO;
import com.helper.vavahelper.models.User.body.LoginResponseDTO;
import com.helper.vavahelper.models.User.body.ResetPasswordDTO;
import com.helper.vavahelper.models.User.body.UserRegisterDTO;
import com.helper.vavahelper.repositories.UserRepository;
import com.helper.vavahelper.service.PasswordResetService;
import com.helper.vavahelper.service.TokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;



@Tag(name = "Authentication", description = "API for user registration and login")
@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired 
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordResetService passwordResetService;

    @Operation(
        summary = "Register a new user",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "New user registration data",
            required = true,
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UserRegisterDTO.class))
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Username already taken")
        }
    )
    @PostMapping("/register")
    public ResponseEntity<String> postMethodRegister(@RequestBody @Valid UserRegisterDTO data){

        if(repository.findByLogin(data.login()) != null) return ResponseEntity
        .badRequest().body("Username already taken.");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserRole role = UserRole.USER;

        //TODO: Fix E-Mail in create Register

        User newUser = new User(data.login(), encryptedPassword, role);
        this.repository.save(newUser);
        return ResponseEntity.ok("User registered successfully.");
    }

    @Operation(
        summary = "Authenticate user and return JWT",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "User login credentials",
            required = true,
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AuthenticationDTO.class))
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "JWT token returned",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = LoginResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
        }
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> postMethodLogin(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User)auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Operation(
        summary = "Request password reset link",
        description = "Sends an email with a password reset link to the user",
        parameters = {
            @io.swagger.v3.oas.annotations.Parameter(
                name = "email",
                description = "User's email/login",
                required = true,
                example = "user@example.com"
            )
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Password reset link sent"),
            @ApiResponse(responseCode = "404", description = "User not found")
        }
    )
    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(
            @RequestParam("email") String email,
            HttpServletRequest request) {
        String appUrl = request.getScheme() + "://" 
                      + request.getServerName() 
                      + ":" + request.getServerPort();
        passwordResetService.createPasswordResetToken(email, appUrl);
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Reset user password",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Token and new password",
            required = true,
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ResetPasswordDTO.class))
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Password reset successful"),
            @ApiResponse(responseCode = "400", description = "Invalid or expired token")
        }
    )
    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody ResetPasswordDTO dto) {
        
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.newPassword());
        
        passwordResetService.resetPassword(dto.token(), encryptedPassword);
        return ResponseEntity.ok().build();
    }

}