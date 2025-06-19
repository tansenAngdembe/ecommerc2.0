--liquibase formatted sql
--changeset tansen:1

CREATE TABLE IF NOT EXISTS cart(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    CONSTRAINT fk_cart_customers FOREIGN KEY (customer_id) REFERENCES customers(id)
);