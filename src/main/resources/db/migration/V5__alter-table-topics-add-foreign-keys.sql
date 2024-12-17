alter table topics
    add user_id bigint,
    add course_id bigint,

    add constraint fk_topics_user_id foreign key(user_id) references users(id),
    add constraint fk_topics_course_id foreign key(course_id) references courses(id)
