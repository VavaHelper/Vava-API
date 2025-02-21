package com.helper.vavahelper.Controllers;

import com.helper.vavahelper.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, 
                           @RequestParam String password,
                           @RequestParam Role role) {
        userService.createUser(username, password, role);
        return "Usuário registrado com sucesso!";
    }
}