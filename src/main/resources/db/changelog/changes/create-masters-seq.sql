--liquibase formatted sql
--changeset <postgres>:<create-masters_seq-sequence>

CREATE SEQUENCE IF NOT EXISTS masters_seq
    START 1
    INCREMENT 1
    OWNED BY masters.id;

--rollback DROP SEQUENCE masters_seq