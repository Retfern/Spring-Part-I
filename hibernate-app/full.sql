BEGIN;

SET search_path TO hibernate;

DROP TABLE IF EXISTS user_details CASCADE;
CREATE TABLE user_details (id bigserial PRIMARY KEY, email VARCHAR(255), city varchar(255));
INSERT INTO user_details (email, city) VALUES
('terminator@gmail.com', 'California'),
('rembo@gmail.com', 'Atlanta'),
('corben_dallas@gmail.com', 'New York');

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (id bigserial PRIMARY KEY, name VARCHAR(255), details_id bigint, FOREIGN KEY (details_id) REFERENCES user_details (id));
INSERT INTO users (name, details_id) VALUES
('Arnold S.', 1),
('Silvester S.', 2),
('Willis B.', 3);

DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders (id bigserial PRIMARY KEY, user_id bigint, FOREIGN KEY (user_id) REFERENCES users (id));
INSERT INTO orders (user_id) VALUES
(1),
(2),
(3);

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price int);
INSERT INTO products (title, price) VALUES
('box', 10),
('sphere', 20),
('maul', 100),
('door', 50),
('camera', 500);

DROP TABLE IF EXISTS order_details CASCADE;
CREATE TABLE order_details (id bigserial PRIMARY KEY, order_id bigint, product_id bigint, current_price int, number_products int, FOREIGN KEY (product_id) REFERENCES products (id), FOREIGN KEY (order_id) REFERENCES orders (id));
INSERT INTO order_details (order_id, product_id, current_price, number_products) VALUES
(1, 1, 5, 2),
(1, 3, 60, 1),
(2, 1, 10, 3),
(3, 4, 40, 1),
(3, 5, 450, 1);

COMMIT;