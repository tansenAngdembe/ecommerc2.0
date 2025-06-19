--liquibase formatted sql
--changeset tansen:2
--preconditions onFail="CONTINUE" onError="HALT"
CREATE TABLE IF NOT EXISTS products(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    quantity BIGINT,
    images_url VARCHAR(255),
    short_description TEXT,
    long_descriptiton TEXT,
    unique_id VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP,
    is_avilable BOOLEAN NOT NULL,
    rating BIGINT,
    category_id BIGINT,
    CONSTRAINT fk_products_categories FOREIGN KEY (category_id) REFERENCES categories(id)
)
