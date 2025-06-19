--liquibase formatted sql
--changeset tansen:1

CREATE TABLE IF NOT EXISTS categories(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT NOT NULL
);