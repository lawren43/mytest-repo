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
CREATE SEQUENCE public.seq_department_id;

CREATE TABLE department
(
  id integer NOT NULL DEFAULT nextval('seq_department_id'),
  name character varying(20),
  CONSTRAINT department_pk PRIMARY KEY (id),
  CONSTRAINT department_name_unique UNIQUE (name)
)
WITH (
  OIDS=FALSE
);
insert into department (name) values ('Information');
insert into department (name) values ('Market');

select * from department;

/* create sequence */
CREATE SEQUENCE public.seq_person_id;

/* create table Person */
CREATE TABLE person
(
   id integer DEFAULT nextval('seq_person_id'), 
   name character varying(30), 
   country character varying(30),
   department_id integer,
   CONSTRAINT person_pk PRIMARY KEY (id),
   CONSTRAINT department_id_fk FOREIGN KEY (department_id)
      REFERENCES department (id) 
) 
WITH (
  OIDS = FALSE
)
;
REATE TABLE person
(
  id integer NOT NULL DEFAULT nextval('seq_person_id'),
  name character varying(30),
  country character varying(30),
  department_id integer,
  CONSTRAINT person_pk PRIMARY KEY (id),
  CONSTRAINT department_id_fk FOREIGN KEY (department_id)
      REFERENCES department (id),
  CONSTRAINT person_name_unique UNIQUE (name)
)
WITH (
  OIDS=FALSE
);

/* insert   */
insert into Person (name, country, department_id) values ('Adam', 'Taiwan', 1);
insert into Person (name, country, department_id) values ('Bob', 'China', 1);
insert into Person (name, country, department_id) values ('Chris', 'Thai', 2);
insert into Person (name, country) values ('Davie', 'U.S.' );


/* select  */
SELECT  * FROM  person;

select * from  department d full outer join person p on p.department_id=d.id;


/* create sequence */
CREATE SEQUENCE public.seq_person_details_id;

/* test sequence   */
select nextval('seq_person_details_id');

CREATE TABLE public.person_details
(
   id integer DEFAULT nextval('seq_person_details_id'), 
   person_id integer, 
   title character varying(20), 
   CONSTRAINT person_details_id PRIMARY KEY (id), 
   CONSTRAINT person_details_person_id_fk FOREIGN KEY (person_id) REFERENCES person (id) ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITH (
  OIDS = FALSE
)
;

insert into person_details (person_id, title) values (1, 'Manager');
insert into person_details (person_id, title) values (2, 'Engineering');
insert into person_details (person_id, title) values (3, 'Programmer');
insert into person_details (person_id, title) values (4, 'Employee');

select * from person_details;

select * from department d full outer join person p on d.id=p.department_id full outer join person_details pd on p.id=pd.person_id;

