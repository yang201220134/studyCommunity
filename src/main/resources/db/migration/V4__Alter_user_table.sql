alter table PUBLIC.USER add avatar_url varchar(100) null;
alter table QUESTION add user_id int null;
alter table QUESTION alter column GMT_CREATE date;

alter table QUESTION alter column GMT_MODIFIED date;
insert into QUESTION(title,USER_ID) values ( '分页测试',194);