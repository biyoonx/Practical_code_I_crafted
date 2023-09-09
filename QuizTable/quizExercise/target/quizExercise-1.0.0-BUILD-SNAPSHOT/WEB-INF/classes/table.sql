-- 오라클 시스템으로 접속해서 select instance from v$thread;로 찾기 여기서는 orcl
-- <property name="url" value="jdbc:oracle:this:@localhost:1521:orcl"></property>

sqlplus system/oracle

CREATE USER khUser IDENTIFIED BY khUser;
GRANT CONNECT, RESOURCE TO khUser;

CONN khUser/khUser;
CREATE TABLE Quiz (
    QUESTION VARCHAR2(100),
    CONTENT VARCHAR2(100),
    ANSWER VARCHAR2(100)
);
INSERT INTO Quiz VALUES('1', '1 + 1 = ', '2');
INSERT INTO Quiz VALUES('2', '5 × 2 = ', '10');
INSERT INTO Quiz VALUES('3', '1, 2, 3, 4, 5 and next number is ', '6');
INSERT INTO Quiz VALUES('4', 'This number is known as a perfect number: ', '3');
INSERT INTO Quiz VALUES('5', 'The smallest even natural number is ', '2');
INSERT INTO Quiz VALUES('6', 'This number is known as a lucky number: ', '7');
INSERT INTO Quiz VALUES('7', 'This number is known as a sign of the devil or as the number of the beast', '666');
INSERT INTO Quiz VALUES('8', '_ _ ! What a Surprise! - Fill in the blank: ', 'oh');
INSERT INTO Quiz VALUES('9', 'Are you funny?', 'yes');
INSERT INTO Quiz VALUES('10', 'What is this?', 'quiz');
SELECT * FROM Quiz;
COMMIT;


-- MySQL
mysql -u spring5 -p
      spring5

USE spring5fs;

CREATE TABLE quiz(
    question VARCHAR(100),
    content VARCHAR(100),
    answer VARCHAR(100)
);
INSERT INTO Quiz VALUES('1', '1 + 1 = ', '2');
INSERT INTO Quiz VALUES('2', '5 × 2 = ', '10');
INSERT INTO Quiz VALUES('3', '1, 2, 3, 4, 5 and next number is ', '6');
INSERT INTO Quiz VALUES('4', 'This number is known as a perfect number: ', '3');
INSERT INTO Quiz VALUES('5', 'The smallest even natural number is ', '2');
INSERT INTO Quiz VALUES('6', 'This number is known as a lucky number: ', '7');
INSERT INTO Quiz VALUES('7', 'This number is known as a sign of the devil or as the number of the beast', '666');
INSERT INTO Quiz VALUES('8', '_ _ ! What a Surprise! - Fill in the blank: ', 'oh');
INSERT INTO Quiz VALUES('9', 'Are you funny?', 'yes');
INSERT INTO Quiz VALUES('10', 'What is this?', 'quiz');
SELECT * FROM Quiz;
COMMIT;