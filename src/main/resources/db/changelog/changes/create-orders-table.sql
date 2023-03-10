--liquibase formatted sql
--changeset <postgres>:<create-orders-table>

CREATE TABLE IF NOT EXISTS     orders (
                               id bigint NOT NULL PRIMARY KEY,
                               acceptance_date date,
                               cost_for_client numeric(38,2),
                               finish_date date,
                               order_status character varying(255),
                               problem_description character varying(255),
                               car_id bigint,
                               CONSTRAINT fkd2p23ixwrrt395glgi9nnbj23 FOREIGN KEY (car_id) REFERENCES cars(id)
);

--rollback DROP TABLE orders