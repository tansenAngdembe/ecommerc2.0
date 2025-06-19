--liquibase formatted sql
--changeset tansen:1
--preconditions onFail="CONTINUE" onError="HALT"
CREATE TABLE IF NOT EXISTS customers(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name varchar(50) NOT NULL,
    last_name varchar(20) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    mobile_number VARCHAR(20) NOT NULL UNIQUE,
    is_active  BOOLEAN DEFAULT TRUE,
    password VARCHAR(100) NOT NULL,
    unique_id varchar(255) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    password_change_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_logged_in_at TIMESTAMP,
    wrong_password_attempt_count BIGINT DEFAULT 0,
    is_account_locked BOOLEAN DEFAULT FALSE,
    locked_time TIMESTAMP,
    profile_picture_url VARCHAR(255)
);