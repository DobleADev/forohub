create table topics(
    id bigint not null auto_increment,
    title varchar(150) not null unique,
    message text,
    creation_date datetime not null,
    status varchar(40) not null,
    active tinyint not null,

    primary key(id)
);