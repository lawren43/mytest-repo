/*
 username:  test_user
 password: test_password
*/
CREATE ROLE test_user LOGIN ENCRYPTED PASSWORD 'md5abdbecd56d5fbd2cdaee3d0fa9e4f434'
   VALID UNTIL 'infinity';


CREATE DATABASE test_db
  WITH ENCODING='UTF8'
       OWNER=test_user
       CONNECTION LIMIT=-1;


/*  switch to test_user before executing following commands   */

/* create sequence */
CREATE SEQUENCE public.seq_person_id;

/* test sequence   */
select nextval('seq_person_id');

/* create table Person */
CREATE TABLE person
(
   id integer DEFAULT nextval('seq_person_id'), 
   name character varying(30), 
   country character varying(30), 
   CONSTRAINT person_pk PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
)
;

/* insert   */
insert into Person (name, country) values ('Adam', 'Taiwan');
insert into Person (name, country) values ('Bob', 'China');
insert into Person (name, country) values ('Chris', 'Thai');
 
/* select  */
SELECT  * FROM  person;

