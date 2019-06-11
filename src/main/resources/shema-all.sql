DROP TABLE IF EXISTS people ;

CREATE TABLE people (
	person_id BIGINT  NOT NULL PRIMARY KEY,
	first_name VARCHAR(20),
	last_name VARCHAR(20)
);


DROP TABLE IF EXISTS fruit;

CREATE TABLE fruit
(
id SERIAL NOT NULL PRIMARY KEY
,name VARCHAR(10)
,price INT
);