create table if not exists audit(
    id          bigserial not null,
    description varchar(255),
    date        timestamp default current_date,
    employee_id bigserial not null,
    primary key (id)
);