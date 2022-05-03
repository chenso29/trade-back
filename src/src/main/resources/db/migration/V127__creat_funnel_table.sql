create table if not exists funnel(
    id                    bigserial not null,
    status_name varchar(255),
    funnel_time varchar(255),
    count int8,
    conversion varchar(255),
    price varchar(255),
    type varchar(255),
    primary key (id)
);