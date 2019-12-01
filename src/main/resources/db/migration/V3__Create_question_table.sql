create table question(
    id int auto_increment primary key ,
    title char(50),
    description text,
    gmt_create bigint,
    gmt_modified bigint,
    creator varchar(130),
    comment_count int default 0,
    like_count int default 0,
    tag varchar(256)


);