--liquibase formatted sql
--changeset <postgres>:<create-masters-table>

CREATE TABLE IF NOT EXISTS      masters (
--                                 id bigint NOT NULL PRIMARY KEY DEFAULT NEXTVAL('masters_seq'),
                                id bigint NOT NULL PRIMARY KEY,
                                father_name character varying(255),
                                name character varying(255),
                                surname character varying(255)
);

--rollback DROP TABLE masters