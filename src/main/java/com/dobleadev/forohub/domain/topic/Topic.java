package com.dobleadev.forohub.domain.topic;

import com.dobleadev.forohub.domain.answer.Answer;
import com.dobleadev.forohub.domain.course.Course;
import com.dobleadev.forohub.domain.user.User;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String message;
    @JsonAlias("creation_date")
    private LocalDateTime creationDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Answer> answers;
    private Boolean active;

    public Topic(String title, String message, User author, Course course) {
        this.title = title;
        this.message = message;
        this.creationDate = LocalDateTime.now();
        this.status = Status.OPENED;
        this.author = author;
        this.course = course;
        this.active = true;
    }

    public void updateData(DataUpdateTopic topic) {
        if (topic.title() != null) {
            this.title = topic.title();
        }

        if (topic.message() != null) {
            this.message = topic.message();
        }
    }

    public void deactivate() {
        this.active = false;
    }
}
