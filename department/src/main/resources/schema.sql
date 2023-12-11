DROP TABLE IF EXISTS Department;

DROP SEQUENCE IF EXISTS Department_SEQ;

create table Department (
    id bigint not null,
    name varchar(255),
    primary key (id)
);

CREATE SEQUENCE Department_SEQ start with 1 increment by 1;
