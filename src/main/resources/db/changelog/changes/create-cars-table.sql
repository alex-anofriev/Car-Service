--liquibase formatted sql
--changeset <postgres>:<create-cars-table>

CREATE TABLE IF NOT EXISTS cars (
                             id bigint NOT NULL PRIMARY KEY,
                             brand character varying(255),
                             car_number character varying(255),
                             is_deleted boolean,
                             model character varying(255),
                             year_of_issue integer,
                             owner_id bigint,
                             CONSTRAINT fkhcsx2hgskre1qwetp67r7qfr FOREIGN KEY (owner_id) REFERENCES owners(id)
);

--rollback DROP TABLE cars