--liquibase formatted sql
--changeset <postgres>:<create-owners_seq-sequence>

CREATE SEQUENCE IF NOT EXISTS owners_seq
    START 1
    INCREMENT 1
    OWNED BY owners.id;

--rollback DROP SEQUENCE owners_seq