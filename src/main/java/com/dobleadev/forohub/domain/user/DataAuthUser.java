package com.dobleadev.forohub.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DataAuthUser(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password
) {
}
