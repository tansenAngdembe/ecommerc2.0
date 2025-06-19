--liquibase formatted sql
--changeset tansen:1

INSERT INTO products (product_name, price, quantity, images_url, short_description,
                      long_descriptiton, unique_id, is_avilable, rating, category_id)
VALUES ('Wireless Mouse', 1500.50, 100, 'https://example.com/images/mouse.jpg', 'Ergonomic wireless mouse',
        'A comfortable and reliable wireless mouse with long battery life.', 'UID001', true, 4, 1),

       ('Gaming Keyboard', 3500.44, 50, 'https://example.com/images/keyboard.jpg', 'RGB mechanical keyboard',
        'Mechanical keyboard with RGB backlight, perfect for gamers.', 'UID002', true, 5, 1),

       ('Noise Cancelling Headphones', 8000.00, 30, 'https://example.com/images/headphones.jpg',
        'Immersive sound experience', 'Premium over-ear headphones with active noise cancellation.', 'UID003', true, 5,
        2),

       ('Smartphone Stand', 700.42, 200, 'https://example.com/images/stand.jpg', 'Adjustable stand for phones',
        'Sturdy and adjustable smartphone stand compatible with all devices.', 'UID004', true, 4, 2),

       ('Bluetooth Speaker', 2500.33, 75, 'https://example.com/images/speaker.jpg', 'Portable speaker with deep bass',
        'Compact and powerful Bluetooth speaker with 10-hour battery life.', 'UID005', true, 4, 3),

       ('USB-C Charger', 1200.33, 150, 'https://example.com/images/charger.jpg', 'Fast charging USB-C adapter',
        '20W USB-C fast charger compatible with modern smartphones.', 'UID006', true, 3, 3),

       ('Laptop Backpack', 4000.33, 60, 'https://example.com/images/backpack.jpg', 'Waterproof backpack',
        'Spacious waterproof backpack with padded laptop compartment.', 'UID007', true, 5, 4),

       ('Webcam 1080p', 3000.33, 40, 'https://example.com/images/webcam.jpg', 'HD webcam for video calls',
        '1080p HD webcam with built-in microphone for streaming and meetings.', 'UID008', true, 4, 4),

       ('LED Monitor 24"', 12000.00, 25, 'https://example.com/images/monitor.jpg', 'Full HD LED monitor',
        '24-inch Full HD LED monitor with HDMI and VGA ports.', 'UID009', true, 5, 5),

       ('Wireless Earbuds', 6000.00, 80, 'https://example.com/images/earbuds.jpg', 'True wireless stereo earbuds',
        'Comfortable and high-quality earbuds with charging case.', 'UID010', true, 4, 5);
