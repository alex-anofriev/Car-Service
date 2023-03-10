--liquibase formatted sql
--changeset <postgres>:<create-orders_seq-sequence>

CREATE SEQUENCE IF NOT EXISTS orders_seq
    START 1
    INCREMENT 1
    OWNED BY orders.id;

--rollback DROP SEQUENCE orders_seq