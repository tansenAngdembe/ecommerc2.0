--liquibase formatted sql
--changeset tansen:3

INSERT INTO categories (category_name, description)
VALUES ('Computer Accessories', 'Devices and peripherals used with computers such as keyboards, mice, webcams, etc.'),

       ('Mobile Accessories', 'Accessories for mobile phones including stands, chargers, and mounts.'),

       ('Audio Equipment', 'Speakers, headphones, earbuds, and other audio-related devices.'),

       ('Office Gear', 'Items used in office or remote work settings like backpacks, webcams, and stands.'),

       ('Display & Monitors', 'Monitors and other screen display equipment used with desktops and laptops.');