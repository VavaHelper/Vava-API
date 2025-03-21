package com.helper.vavahelper.models.User.body;

import com.helper.vavahelper.models.User.UserRole;

public record UserRegisterDTO(String login, String password, UserRole role) {
    
}
