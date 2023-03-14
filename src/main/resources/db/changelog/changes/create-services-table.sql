--liquibase formatted sql
--changeset <postgres>:<create-services-table>

CREATE TABLE IF NOT EXISTS services (
                                 id bigint NOT NULL PRIMARY KEY,
                                 service_status character varying(255),
                                 price numeric(38,2),
                                 repair_agreement boolean,
                                 master_id bigint,
                                 order_id bigint,
                                is_service boolean,
                                CONSTRAINT fk565hd47u11qajksyfi4gqrfu0 FOREIGN KEY (master_id) REFERENCES masters(id),
                                CONSTRAINT fknmykpsxcf4bgaecn9g3vdbc1s FOREIGN KEY (order_id) REFERENCES orders(id)
);

--rollback DROP TABLE services