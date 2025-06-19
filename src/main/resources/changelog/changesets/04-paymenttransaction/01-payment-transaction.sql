--liquibase formatted sql
--changeset tansen:1
CREATE TABLE IF NOT EXISTS payment_transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    transaction_code VARCHAR(100) NOT NULL UNIQUE,
    transaction_uuid VARCHAR(100) NOT NULL UNIQUE,
    status VARCHAR(50) NOT NULL,
    transaction_signature VARCHAR(100) NOT NULL UNIQUE,
    transaction_type VARCHAR(100) NOT NULL,
    transaction_total_amount VARCHAR(100) NOT NULL,
    transaction_product_code VARCHAR(100) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    order_id BIGINT NOT NULL,
    CONSTRAINT fk_payment_transaction_orders FOREIGN KEY (order_id) REFERENCES orders(id)
    );
