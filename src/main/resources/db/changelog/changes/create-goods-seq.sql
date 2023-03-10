--liquibase formatted sql
--changeset <postgres>:<create-goods_seq-sequence>

CREATE SEQUENCE IF NOT EXISTS goods_seq
    START 1
    INCREMENT 1
    OWNED BY goods.id;

--rollback DROP SEQUENCE goods_seq