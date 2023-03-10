--liquibase formatted sql
--changeset <postgres>:<create-masters_orders-table>

CREATE TABLE IF NOT EXISTS masters_orders (
                            master_id bigint NOT NULL PRIMARY KEY,
                            order_id bigint NOT NULL,
                            CONSTRAINT fklehmwwquvu45bn07mp6ow8mqi FOREIGN KEY (master_id) REFERENCES masters(id),
                            CONSTRAINT fkr1v8bwcjbn9om2kl47alwabi3 FOREIGN KEY (order_id) REFERENCES orders(id)
);

--rollback DROP TABLE masters_orders