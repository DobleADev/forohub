create table answers(
    id bigint not null auto_increment,
    message text,
    topic_id bigint,
    creation_date datetime not null,
    user_id bigint,
    solution tinyint not null,

    primary key(id),
    constraint fk_answers_topic_id foreign key(topic_id) references topics(id),
    constraint fk_answers_user_id foreign key(user_id) references users(id)
);