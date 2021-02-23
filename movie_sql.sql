DROP TABLE movie_user;
DROP TABLE temp_user;
CREATE TABLE movie_user(
	uno INT PRIMARY KEY auto_increment,
    uid VARCHAR(50) NOT NULL,
    upw VARCHAR(50) NOT NULL,
    nickName VARCHAR(20) NOT NULL UNIQUE,
    cert CHAR(3) DEFAULT 'N'
);

DESC movie_user;
SELECT * FROM movie_user;

INSERT INTO movie_user(uid,upw,nickName,cert) VALUES(2,2,"master1",'Y');

CREATE TABLE temp_user(
	uid VARCHAR(50) PRIMARY KEY NOT NULL,
    cert VARCHAR(200) NOT NULL
);

SELECT * FROM temp_user;

DROP TABLE tbl_review;
CREATE TABLE tbl_review(
	rno INT PRIMARY KEY auto_increment,
    mid INT NOT NULL,
    nickName VARCHAR(20) NOT NULL,
	star DOUBLE default 0,
    review text NULL,
    liked INT default 0,
    regdate TIMESTAMP default now()
);
DESC tbl_review;
SELECT * FROM tbl_review;
CREATE TABLE tbl_like(
	rno INT NOT NULL,
    mid INT NOT NULL,
    nickName VARCHAR(20) NOT NULL
);
SELECT * FROM tbl_like;
INSERT INTO tbl_like(rno, mid, nickName) VALUES(2, 324857, "master");
DROP TABLE tbl_like;

SELECT now(6);

