create sequence global_seq start 1 increment 1

    create table meals (
        id int4 not null,
        calories int4 not null check (calories>=10 AND calories<=5000),
        date_time timestamp not null,
        description varchar(255) not null,
        user_id int4 not null,
        primary key (id)
    )

    create table user_roles (
        user_id int4 not null,
        role varchar(255)
    )

    create table users (
        id int4 not null,
        name varchar(255) not null,
        calories_per_day default 2000,
        email varchar(255) not null,
        enabled boolean not null,
        password varchar(255) not null,
        registered timestamp default now(),
        primary key (id)
    )

    alter table meals 
        add constraint meals_unique_user_datetime_idx unique (user_id, date_time)

    alter table users 
        add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)

    alter table meals 
        add constraint FK677c66qpjr7234luomahc1ale 
        foreign key (user_id) 
        references users

    alter table user_roles 
        add constraint FKhfh9dx7w3ubf1co1vdev94g3f 
        foreign key (user_id) 
        references users
