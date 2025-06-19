--liquibase formatted sql
--changeset tansen:4
CREATE TABLE IF NOT EXISTS orders_items(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity BIGINT,
    price DECIMAL(10, 2),
    order_id BIGINT NOT NULL,
    product_id  BIGINT NOT NULL,
    CONSTRAINT fk_ordersitems_orders FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_ordersitem_procducts FOREIGN KEY (product_id) REFERENCES  products(id) ON DELETE CASCADE ON UPDATE CASCADE
)