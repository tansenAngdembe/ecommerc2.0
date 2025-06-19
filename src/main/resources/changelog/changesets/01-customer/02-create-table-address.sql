--liquibase formatted sql
--changeset tensen:2
CREATE TABLE IF NOT EXISTS address (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(25) NOT NULL,
    city VARCHAR(25) NOT NULL,
    state VARCHAR(25) NOT NULL,
    zip VARCHAR(25) NOT NULL,
    phone VARCHAR(15) NOT NULL UNIQUE,
    customer_id BIGINT NOT NULL,
    CONSTRAINT fk_address_customers FOREIGN KEY (customer_id) REFERENCES customers(id)
)