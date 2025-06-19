--liquibase formatted sql
--changeset tansen:3

CREATE TABLE IF NOT EXISTS orders(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_price DECIMAL(10, 2),
    payment_method VARCHAR(20),
    status VARCHAR(20) NOT NULL,
    payment_status VARCHAR(20)  NOT NULL,
    customer_id BIGINT NOT NULL,
    shipping_address_id BIGINT NOT NULL,
    CONSTRAINT fk_orders_customers FOREIGN KEY (customer_id) REFERENCES customers(id),
    CONSTRAINT fk_orders_address FOREIGN KEY (shipping_address_id) REFERENCES address(id)
);