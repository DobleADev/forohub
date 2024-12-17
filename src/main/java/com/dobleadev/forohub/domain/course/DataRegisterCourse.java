package com.dobleadev.forohub.domain.course;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterCourse(
        @NotBlank
    String name,
        @NotBlank
    Category category
) {
}
