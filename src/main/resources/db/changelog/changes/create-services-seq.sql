--liquibase formatted sql
--changeset <postgres>:<create-services_seq-sequence>

CREATE SEQUENCE IF NOT EXISTS services_seq
    START 1
    INCREMENT 1
    OWNED BY services.id;

--rollback DROP SEQUENCE services_seq