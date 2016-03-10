# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table admin (
  username                  varchar(255),
  password                  varchar(255))
;

create table basic_user (
  dtype                     varchar(10) not null,
  id                        bigserial not null,
  password                  varchar(255),
  name                      varchar(255),
  birth_date                timestamp,
  department_no             integer,
  constraint pk_basic_user primary key (id))
;

create table course (
  course_id                 bigserial not null,
  title                     varchar(255),
  constraint pk_course primary key (course_id))
;

create table exam (
  id                        bigserial not null,
  title                     varchar(255),
  percentage                float,
  constraint pk_exam primary key (id))
;

create table grading_group (
  id                        bigserial not null,
  title                     varchar(255),
  percentage                float,
  constraint pk_grading_group primary key (id))
;

create table offered_course (
  id                        bigserial not null,
  course_course_id          bigint,
  lecturer_id               bigint,
  semester                  varchar(255),
  room                      varchar(255),
  lecture_time              varchar(255),
  group_id                  integer,
  final_exam_time           timestamp,
  constraint pk_offered_course primary key (id))
;

create table taken_course (
  offered_course_id         bigint,
  student_id                bigint)
;

create table teaching_assistance (
  id                        bigserial not null,
  student_id                bigint,
  course                    varchar(255),
  constraint pk_teaching_assistance primary key (id))
;

alter table offered_course add constraint fk_offered_course_course_1 foreign key (course_course_id) references course (course_id);
create index ix_offered_course_course_1 on offered_course (course_course_id);
alter table offered_course add constraint fk_offered_course_lecturer_2 foreign key (lecturer_id) references basic_user (id);
create index ix_offered_course_lecturer_2 on offered_course (lecturer_id);
alter table taken_course add constraint fk_taken_course_offeredCourse_3 foreign key (offered_course_id) references offered_course (id);
create index ix_taken_course_offeredCourse_3 on taken_course (offered_course_id);
alter table taken_course add constraint fk_taken_course_student_4 foreign key (student_id) references basic_user (id);
create index ix_taken_course_student_4 on taken_course (student_id);
alter table teaching_assistance add constraint fk_teaching_assistance_student_5 foreign key (student_id) references basic_user (id);
create index ix_teaching_assistance_student_5 on teaching_assistance (student_id);



# --- !Downs

drop table if exists admin cascade;

drop table if exists basic_user cascade;

drop table if exists course cascade;

drop table if exists exam cascade;

drop table if exists grading_group cascade;

drop table if exists offered_course cascade;

drop table if exists taken_course cascade;

drop table if exists teaching_assistance cascade;

