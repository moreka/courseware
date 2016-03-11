# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table basic_user (
  dtype                     varchar(10) not null,
  id                        varchar(255) not null,
  password                  varchar(255),
  name                      varchar(255),
  birth_date                timestamp,
  department_no             integer,
  constraint pk_basic_user primary key (id))
;

create table course (
  course_id                 bigserial not null,
  title                     varchar(255),
  department_no             integer,
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
  lecturer_id               varchar(255),
  semester                  varchar(255),
  room                      varchar(255),
  lecture_time              varchar(255),
  group_id                  integer,
  final_exam_time           timestamp,
  constraint pk_offered_course primary key (id))
;

create table syllabus_item (
  id                        bigserial not null,
  parent_id                 bigint,
  offered_course_id         bigint,
  title                     varchar(255),
  item_order                integer,
  constraint pk_syllabus_item primary key (id))
;

create table taken_course (
  offered_course_id         bigint,
  student_id                varchar(255))
;

create table teaching_assistance (
  id                        bigserial not null,
  student_id                varchar(255),
  offered_course_id         bigint,
  constraint pk_teaching_assistance primary key (id))
;

create table token (
  id                        bigserial not null,
  access_type               integer,
  unique_id                 varchar(255),
  user_id                   varchar(255),
  constraint ck_token_access_type check (access_type in (0,1,2)),
  constraint pk_token primary key (id))
;

alter table offered_course add constraint fk_offered_course_course_1 foreign key (course_course_id) references course (course_id);
create index ix_offered_course_course_1 on offered_course (course_course_id);
alter table offered_course add constraint fk_offered_course_lecturer_2 foreign key (lecturer_id) references basic_user (id);
create index ix_offered_course_lecturer_2 on offered_course (lecturer_id);
alter table syllabus_item add constraint fk_syllabus_item_parent_3 foreign key (parent_id) references syllabus_item (id);
create index ix_syllabus_item_parent_3 on syllabus_item (parent_id);
alter table syllabus_item add constraint fk_syllabus_item_offeredCourse_4 foreign key (offered_course_id) references offered_course (id);
create index ix_syllabus_item_offeredCourse_4 on syllabus_item (offered_course_id);
alter table taken_course add constraint fk_taken_course_offeredCourse_5 foreign key (offered_course_id) references offered_course (id);
create index ix_taken_course_offeredCourse_5 on taken_course (offered_course_id);
alter table taken_course add constraint fk_taken_course_student_6 foreign key (student_id) references basic_user (id);
create index ix_taken_course_student_6 on taken_course (student_id);
alter table teaching_assistance add constraint fk_teaching_assistance_student_7 foreign key (student_id) references basic_user (id);
create index ix_teaching_assistance_student_7 on teaching_assistance (student_id);
alter table teaching_assistance add constraint fk_teaching_assistance_offered_8 foreign key (offered_course_id) references offered_course (id);
create index ix_teaching_assistance_offered_8 on teaching_assistance (offered_course_id);
alter table token add constraint fk_token_user_9 foreign key (user_id) references basic_user (id);
create index ix_token_user_9 on token (user_id);



# --- !Downs

drop table if exists basic_user cascade;

drop table if exists course cascade;

drop table if exists exam cascade;

drop table if exists grading_group cascade;

drop table if exists offered_course cascade;

drop table if exists syllabus_item cascade;

drop table if exists taken_course cascade;

drop table if exists teaching_assistance cascade;

drop table if exists token cascade;

