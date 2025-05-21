package com.helper.vavahelper.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;

    //Filtro de Segurança de Rotas:
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .cors(cors -> cors.and())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(HttpMethod.POST, "/auth/forgot-password", "/auth/reset-password").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                    .requestMatchers(HttpMethod.OPTIONS, "/agents/*/with-skills").permitAll()
                    .requestMatchers(HttpMethod.GET, "/agents/*/with-skills").permitAll()
                    .requestMatchers(HttpMethod.GET, "/agents/**").permitAll()
                    .requestMatchers("/h2/**").permitAll()
                    .requestMatchers("/docs/","/docs/**",
                    "/swagger/","/swagger/**","/swagger-ui/**",
                    "/swagger-ui.html","/v3/api-docs/**",
                    "/swagger-resources/**","/webjars/**").permitAll()
                    .anyRequest().authenticated()
                )
                .addFilterAfter(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .headers(headers -> headers
                .frameOptions().sameOrigin()  // Permite iframes no H2
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ 
        return new BCryptPasswordEncoder(); 
    }
}
