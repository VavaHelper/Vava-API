package com.helper.vavahelper.models.User.body;

public record ResetPasswordDTO(String token, String newPassword) {}