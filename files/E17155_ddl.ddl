-- Generated by Oracle SQL Developer Data Modeler 17.4.0.355.2121
--   at:        2019-05-21 11:36:19 EEST
--   site:      Oracle Database 12c
--   type:      Oracle Database 12c



CREATE TABLE cinema (
    cinema_location   VARCHAR2(40) NOT NULL
);

ALTER TABLE cinema ADD CONSTRAINT cinema_pk PRIMARY KEY ( cinema_location );

CREATE TABLE customer (
    fullname   VARCHAR2(40),
    phonenum   VARCHAR2(40) NOT NULL,
    "e-mail"   VARCHAR2(40)
);

ALTER TABLE customer ADD CONSTRAINT customer_pk PRIMARY KEY ( phonenum );

CREATE TABLE movie (
    movie_name       VARCHAR2(40) NOT NULL,
    movie_plot       VARCHAR2(150),
    movie_director   VARCHAR2(40),
    movie_actors     VARCHAR2(150),
    movie_gender     VARCHAR2(40),
    movie_length     VARCHAR2(40)
);

ALTER TABLE movie ADD CONSTRAINT movie_pk PRIMARY KEY ( movie_name );

CREATE TABLE movie_theater (
    mt_name                  VARCHAR2(40) NOT NULL,
    mt_rows                  INTEGER,
    cinema_cinema_location   VARCHAR2(40) NOT NULL,
    mt_columns               INTEGER
);

ALTER TABLE movie_theater ADD CONSTRAINT movie_theater_pk PRIMARY KEY ( mt_name );

CREATE TABLE proboli (
    proboli_start            VARCHAR2(40) NOT NULL,
    movie_movie_name         VARCHAR2(40) NOT NULL,
    movie_theater_mt_name    VARCHAR2(40) NOT NULL,
    cinema_cinema_location   VARCHAR2(40) NOT NULL,
    availability             INTEGER
);

ALTER TABLE proboli
    ADD CONSTRAINT proboli_pk PRIMARY KEY ( proboli_start,
    movie_theater_mt_name,
    movie_movie_name,
    cinema_cinema_location );

CREATE TABLE reservation (
    res_id                           VARCHAR2(20) NOT NULL,
    ticketsnum                       INTEGER,
    status                           VARCHAR2(40),
    proboli_proboli_start            VARCHAR2(40) NOT NULL,
    customer_phonenum                VARCHAR2(40) NOT NULL,
    proboli_movie_theater_mt_name    VARCHAR2(40) NOT NULL,
    proboli_movie_movie_name         VARCHAR2(40) NOT NULL,
    proboli_cinema_cinema_location   VARCHAR2(40) NOT NULL
);

ALTER TABLE reservation ADD CONSTRAINT reservation_pk PRIMARY KEY ( res_id );

CREATE TABLE seat (
    "row"                            INTEGER NOT NULL,
    "column"                         INTEGER NOT NULL,
    status                           VARCHAR2(40),
    proboli_proboli_start            VARCHAR2(40) NOT NULL,
    proboli_mt_name                  VARCHAR2(40) NOT NULL,
    proboli_movie_name               VARCHAR2(40) NOT NULL,
    proboli_cinema_cinema_location   VARCHAR2(40) NOT NULL
);

ALTER TABLE seat ADD CONSTRAINT seat_pk PRIMARY KEY ( "row",
"column" );

ALTER TABLE movie_theater
    ADD CONSTRAINT movie_theater_cinema_fk FOREIGN KEY ( cinema_cinema_location )
        REFERENCES cinema ( cinema_location );

ALTER TABLE proboli
    ADD CONSTRAINT proboli_cinema_fk FOREIGN KEY ( cinema_cinema_location )
        REFERENCES cinema ( cinema_location );

ALTER TABLE proboli
    ADD CONSTRAINT proboli_movie_fk FOREIGN KEY ( movie_movie_name )
        REFERENCES movie ( movie_name );

ALTER TABLE proboli
    ADD CONSTRAINT proboli_movie_theater_fk FOREIGN KEY ( movie_theater_mt_name )
        REFERENCES movie_theater ( mt_name );

ALTER TABLE reservation
    ADD CONSTRAINT reservation_customer_fk FOREIGN KEY ( customer_phonenum )
        REFERENCES customer ( phonenum );

ALTER TABLE reservation
    ADD CONSTRAINT reservation_proboli_fk FOREIGN KEY ( proboli_proboli_start,
    proboli_movie_theater_mt_name,
    proboli_movie_movie_name,
    proboli_cinema_cinema_location )
        REFERENCES proboli ( proboli_start,
        movie_theater_mt_name,
        movie_movie_name,
        cinema_cinema_location );

ALTER TABLE seat
    ADD CONSTRAINT seat_proboli_fk FOREIGN KEY ( proboli_proboli_start,
    proboli_mt_name,
    proboli_movie_name,
    proboli_cinema_cinema_location )
        REFERENCES proboli ( proboli_start,
        movie_theater_mt_name,
        movie_movie_name,
        cinema_cinema_location );



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                             7
-- CREATE INDEX                             0
-- ALTER TABLE                             14
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- TSDP POLICY                              0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
