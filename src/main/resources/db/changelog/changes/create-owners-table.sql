--liquibase formatted sql
--changeset <postgres>:<create-owners-table>

CREATE TABLE IF NOT EXISTS owners (
                             id bigint NOT NULL PRIMARY KEY
);

--rollback DROP TABLE owners