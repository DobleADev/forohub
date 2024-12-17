package com.dobleadev.forohub.domain.topic;

import com.dobleadev.forohub.domain.answer.Answer;
import com.dobleadev.forohub.domain.course.Course;
import com.dobleadev.forohub.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;

public record DataResultTopic(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        String status
//        User author,
//        Course course
) {
    public DataResultTopic(Topic topic) {
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatus().toString()
//                topic.getAuthor(),
//                topic.getCourse()
        );
    }
}
