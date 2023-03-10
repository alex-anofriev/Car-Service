--liquibase formatted sql
--changeset <postgres>:<create-orders_goods-table>

CREATE TABLE IF NOT EXISTS orders_goods (
                                     order_id bigint NOT NULL,
                                     goods_id bigint NOT NULL,
                                     CONSTRAINT fkaoqjqu5li3448xo657dvp6teq FOREIGN KEY (order_id) REFERENCES orders(id),
                                     CONSTRAINT fknsv6m7fvy9pmg1b122f7o62x1 FOREIGN KEY (goods_id) REFERENCES goods(id)
);

--rollback DROP TABLE orders_goods