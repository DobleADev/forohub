package com.dobleadev.forohub.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterTopic(
        @NotBlank
        String title,
        String message,
        @NotNull
        Long author_id,
        @NotNull
        Long course_id
) {
}
