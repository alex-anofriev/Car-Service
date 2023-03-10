--liquibase formatted sql
--changeset <postgres>:<create-goods-table>

CREATE TABLE IF NOT EXISTS goods (
                              id bigint NOT NULL PRIMARY KEY,
                              name character varying(255),
                              price numeric(38,2)
);

--rollback DROP TABLE goods