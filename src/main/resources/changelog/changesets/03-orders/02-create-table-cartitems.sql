--liquibase formatted sql
--changeset tansen:2

CREATE TABLE IF NOT EXISTS cart_items(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity BIGINT,
    is_deleted BOOLEAN DEFAULT FALSE,
    cart_id BIGINT,
    product_id BIGINT,
    CONSTRAINT fk_cartitems_cart FOREIGN KEY (cart_id) REFERENCES cart(id),
    CONSTRAINT fk_cartitems_products FOREIGN KEY (product_id) REFERENCES products(id)
);