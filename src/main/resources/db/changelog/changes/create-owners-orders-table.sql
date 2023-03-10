--liquibase formatted sql
--changeset <postgres>:<create-owners_orders-table>

CREATE TABLE IF NOT EXISTS owners_orders (
                                      owner_id bigint NOT NULL,
                                      order_id bigint NOT NULL,
                                      CONSTRAINT fkhdu8v0nt2g2wtp40r58tvfy4b FOREIGN KEY (order_id) REFERENCES orders(id),
                                        CONSTRAINT fk72iccnam7p59oel627kryqyv2 FOREIGN KEY (owner_id) REFERENCES owners(id)
);

--rollback DROP TABLE owners_orders