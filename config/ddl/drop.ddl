
    alter table meals 
        drop constraint FK677c66qpjr7234luomahc1ale

    alter table user_roles 
        drop constraint FKhfh9dx7w3ubf1co1vdev94g3f

    drop table if exists meals cascade

    drop table if exists user_roles cascade

    drop table if exists users cascade

    drop sequence global_seq
