package com.vehiculecarepro.dto;

public record RegisterRequest(
        String username,
        String password,
        String confirmPassword
) {}
