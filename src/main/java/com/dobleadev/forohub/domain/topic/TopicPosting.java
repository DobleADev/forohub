package com.dobleadev.forohub.domain.topic;

import com.dobleadev.forohub.domain.course.CourseRepository;
import com.dobleadev.forohub.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicPosting {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Topic Post(DataRegisterTopic topic) {
        var user = userRepository.getReferenceById(topic.author_id());
        var course = courseRepository.getReferenceById(topic.course_id());
        return new Topic(topic.title(), topic.message(), user, course);
    }

}
