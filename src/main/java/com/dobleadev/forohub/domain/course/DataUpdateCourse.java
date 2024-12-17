package com.dobleadev.forohub.domain.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUpdateCourse(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotBlank
        Category category
) {
}
