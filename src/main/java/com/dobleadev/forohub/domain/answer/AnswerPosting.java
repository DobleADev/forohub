package com.dobleadev.forohub.domain.answer;

import com.dobleadev.forohub.domain.topic.TopicRepository;
import com.dobleadev.forohub.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerPosting {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    UserRepository userRepository;

    public Answer Post(DataRegisterAnswer answer) {
        var topic = topicRepository.getReferenceById(answer.topic_id());
        var user = userRepository.getReferenceById(answer.user_id());
        return new Answer(answer.message(), topic, user);
    }

}
