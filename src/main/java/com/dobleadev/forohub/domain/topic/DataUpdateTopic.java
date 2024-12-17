package com.dobleadev.forohub.domain.topic;

import java.time.LocalDateTime;

public record DataUpdateTopic(
        Long id,
        String title,
        String message
) {
}
