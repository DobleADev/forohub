package com.dobleadev.forohub.domain.answer;

import com.dobleadev.forohub.domain.topic.Topic;
import com.dobleadev.forohub.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "answers")
@Entity(name = "Answer")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String message;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;
    private LocalDateTime creationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;
    private Boolean solution;
    private Boolean active;

    public Answer(String message, Topic topic, User author) {
        this.message = message;
        this.topic = topic;
        this.author = author;
        this.creationDate = LocalDateTime.now();
        this.solution = false;
        this.active = true;
    }

    public void updateData(DataUpdateAnswer newAnswer) {
        this.message = newAnswer.message();
    }

    public void deactivate() {
        this.active = false;
    }
}
