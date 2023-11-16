create table faculty
(
    id   serial primary key,
    name varchar not null unique
);

create table departments
(
    id         serial primary key unique,
    name       varchar not null,
    faculty_id int     not null,

    foreign key (faculty_id) references faculty (id)
);

create table profile
(
    id            serial primary key unique,
    name          varchar not null,
    faculty_id    int not null,
    department_id int  not null,

    foreign key (faculty_id) references faculty (id),
    foreign key (department_id) references departments (id)
);

create table students
(
    id            serial  primary key,
    name          varchar not null,
    surname       varchar not null,
    studentid_num varchar unique,
    course        int not null,
    group_num     int not null,
    profile_id    int not null,

    foreign key (profile_id) references profile (id)
);

create table profile_student(
    profile_id serial references profile (id),
    student_id serial references profile (id),

    PRIMARY KEY (profile_id, student_id)
);