package com.dobleadev.forohub.domain.answer;

import com.dobleadev.forohub.domain.topic.Topic;
import com.dobleadev.forohub.domain.user.User;

import java.time.LocalDateTime;

public record DataListAnswer(
        Long id,
        String message,
        String topicName,
        LocalDateTime creationDate,
        String authorName,
        Boolean solution
) {
    public DataListAnswer(Answer answer) {
        this(answer.getId(), answer.getMessage(), answer.getTopic().getTitle(), answer.getCreationDate(), answer.getAuthor().getName(), answer.getSolution());
    }
}
