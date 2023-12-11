DROP TABLE IF EXISTS Employee;

DROP SEQUENCE IF EXISTS Employee_SEQ;

create table Employee (
    deptId bigint,
    id bigint not null,
    name varchar(255),
    primary key (id)
);

CREATE SEQUENCE Employee_SEQ start with 1 increment by 1;
