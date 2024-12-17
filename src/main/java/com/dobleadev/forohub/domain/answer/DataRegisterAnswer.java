package com.dobleadev.forohub.domain.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterAnswer(
        @NotBlank()
        String message,
        @NotNull()
        Long topic_id,
        @NotNull()
        Long user_id
) {
}
