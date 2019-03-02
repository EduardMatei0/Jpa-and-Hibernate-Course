insert into course(id, name, created_date, last_updated_date, is_deleted) values (10001, 'JPA in 50 steps', sysdate, sysdate, false);
insert into course(id, name, created_date, last_updated_date, is_deleted) values (10002, 'Spring in 50 steps', sysdate, sysdate, false);
insert into course(id, name, created_date, last_updated_date, is_deleted) values (10003, 'SpringBoot in 50 steps', sysdate, sysdate, false);


insert into passport(id, number) values (40001, 'E93491');
insert into passport(id, number) values (40002, 'B31257');
insert into passport(id, number) values (40003, 'C312345');

insert into student(id, name, passport_id) values (20001, 'Eduard', 40001);
insert into student(id, name, passport_id) values (20002, 'Cristina', 40002);
insert into student(id, name, passport_id) values (20003, 'Alexandru', 40003);


insert into review(id, rating, description, course_id) values (50001, 'ONE' ,'Great course', 10001);
insert into review(id, rating, description, course_id) values(50002, 'TWO', 'A little complex', 10001);
insert into review(id, rating, description, course_id) values(50003, 'FIVE', 'A weak course', 10003);

insert into student_course(student_id, course_id)
values (20001,10001);
insert into student_course(student_id, course_id)
values (20002,10001);
insert into student_course(student_id, course_id)
values (20003,10001);
insert into student_course(student_id, course_id)
values (20001,10003);