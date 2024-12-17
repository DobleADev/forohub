package com.dobleadev.forohub.domain.topic;

import com.dobleadev.forohub.domain.answer.Answer;
import com.dobleadev.forohub.domain.answer.DataListAnswer;
import com.dobleadev.forohub.domain.course.Course;
import com.dobleadev.forohub.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DataDetailsTopic(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        String status,
        String authorName,
        Course course,
        List<DataListAnswer> answers
) {
    public DataDetailsTopic(Topic topic) {
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatus().toString(),
                topic.getAuthor().getName(),
                topic.getCourse(),
                topic.getAnswers().stream()
                        .map(DataListAnswer::new).collect(Collectors.toList())
        );
    }
}