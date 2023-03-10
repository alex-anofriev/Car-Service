--liquibase formatted sql
--changeset <postgres>:<create-car_seq-sequence>

CREATE SEQUENCE IF NOT EXISTS cars_seq
    START 1
    INCREMENT 1
    OWNED BY cars.id;

--rollback DROP SEQUENCE car_seq