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

DROP TABLE IF EXISTS country CASCADE;
CREATE TABLE country (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO country (name) VALUES
('China'),
('Russia'),
('America'),
('Germany'),
('Taiwan'),
('France'),
('Korea');

DROP TABLE IF EXISTS company CASCADE;
CREATE TABLE company (id bigserial PRIMARY KEY, name VARCHAR(255), country_id bigint, FOREIGN KEY (country_id) REFERENCES country (id));
INSERT INTO company (name, country_id) VALUES
('Samsung', 7),
('WICK', 4),
('FlowresShop', 6),
('Huawei', 1),
('Строймаркет', 2),
('Sunlight', 2),
('ErichKrause', 4),
('Wistron', 5),
('JustUsa', 3);

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price int, company_id bigint, FOREIGN KEY (company_id) REFERENCES company (id));
INSERT INTO products (title, price, company_id) VALUES
('box', 10, 8),
('sphere', 20, 7),
('maul', 100, 5),
('door', 50, 5),
('camera', 5000, 1),
('pen', 15, 7),
('phone', 20000, 1),
('earphone', 1000, 4),
('ring', 500, 6),
('paper', 120, 7),
('medicine', 200, 2),
('note', 40, 7),
('bottle', 5, 2),
('lamp', 60, 9),
('flower', 70, 3),
('comb', 30, 9),
('rubber', 9, 7),
('tape', 45, 6),
('toy', 600, 8),
('board', 100, 5),
('garland', 350, 5);

DROP TABLE IF EXISTS order_details CASCADE;
CREATE TABLE order_details (id bigserial PRIMARY KEY, order_id bigint, product_id bigint, current_price int, number_products int, FOREIGN KEY (product_id) REFERENCES products (id), FOREIGN KEY (order_id) REFERENCES orders (id));
INSERT INTO order_details (order_id, product_id, current_price, number_products) VALUES
(1, 1, 5, 2),
(1, 3, 60, 1),
(2, 1, 10, 3),
(3, 4, 40, 1),
(3, 5, 450, 1);

DROP TABLE IF EXISTS employees_details CASCADE;
CREATE TABLE employees_details (id bigserial PRIMARY KEY, email VARCHAR(255), city varchar(255));
INSERT INTO employees_details (email, city) VALUES
('terminator@gmail.com', 'California'),
('rembo@gmail.com', 'Atlanta'),
('corben_dallas@gmail.com', 'New York');

DROP TABLE IF EXISTS employees CASCADE;
CREATE TABLE employees (id bigserial PRIMARY KEY, name VARCHAR(255), details_id bigint, FOREIGN KEY (details_id) REFERENCES employees_details (id));
INSERT INTO employees (name, details_id) VALUES
('Arnold S.', 1),
('Silvester S.', 2),
('Willis B.', 3);

DROP TABLE IF EXISTS simple_items CASCADE;
CREATE TABLE simple_items (id bigserial PRIMARY KEY, title VARCHAR(255), price int);
INSERT INTO simple_items (title, price) VALUES
('box', 10),
('sphere', 20),
('maul', 100),
('door', 50),
('camera', 500);

DROP TABLE IF EXISTS books CASCADE;
CREATE TABLE books (id bigserial PRIMARY KEY, title VARCHAR(255));
INSERT INTO books (title) VALUES
('Mistborn'),
('Neverwhere'),
('Ambers Chronicles'),
('Harry Potter'),
('Lockwood & Co.'),
('Foundation Trilogy'),
('Liveship Traders Trilogy'),
('A Night in the Lonesome October'),
('Da Vinci Code'),
('Lord of the Ring');

DROP TABLE IF EXISTS readers CASCADE;
CREATE TABLE readers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO readers (name) VALUES
('Alexander'),
('Bob');

DROP TABLE IF EXISTS books_readers CASCADE;
CREATE TABLE books_readers (book_id bigint, reader_id bigint, FOREIGN KEY (book_id) REFERENCES books (id), FOREIGN KEY (reader_id) REFERENCES readers (id));
INSERT INTO books_readers (book_id, reader_id) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 1),
(1, 2),
(2, 2);

DROP TABLE IF EXISTS universities CASCADE;
CREATE TABLE universities (id bigserial PRIMARY KEY, title VARCHAR(255));
INSERT INTO universities (title) VALUES
('DSTU'),
('NPU');

DROP TABLE IF EXISTS students CASCADE;
CREATE TABLE students (id bigserial PRIMARY KEY, name VARCHAR(255), university_id bigint REFERENCES universities (id));
INSERT INTO students (name, university_id) VALUES
('Alexander', 1),
('Bob', 2),
('John', 1);

DROP TABLE IF EXISTS validation_beans CASCADE;
CREATE TABLE validation_beans (id bigserial PRIMARY KEY, email VARCHAR(255), priority int DEFAULT 5, postal_code varchar(6), created_at timestamp, updated_at timestamp);
INSERT INTO validation_beans (email, priority, postal_code) VALUES
('data@mail.ru', 5, '100000');

DROP TABLE IF EXISTS items CASCADE;
CREATE TABLE items (id bigserial PRIMARY KEY, title VARCHAR(255), price numeric(6, 2));
INSERT INTO items (title, price) VALUES
('milk', 79.90),
('bread', 24.90),
('butter', 220.00),
('cheese', 350.55),
('coca-cola', 69.90);

DROP TABLE IF EXISTS passports CASCADE;
CREATE TABLE passports (pserial int, pnumber int, registration_address VARCHAR(255), PRIMARY KEY (pserial, pnumber));
INSERT INTO passports (pserial, pnumber, registration_address) VALUES
(6080, 965400, 'country.state.city.street');

DROP TABLE IF EXISTS citizens CASCADE;
CREATE TABLE citizens (id bigserial, name varchar(255), passport_serial int, passport_number int, CONSTRAINT fk_passport FOREIGN KEY (passport_serial, passport_number) REFERENCES passports (pserial, pnumber));
INSERT INTO citizens (name, passport_serial, passport_number) VALUES
('Bobby', 6080, 965400);

DROP TABLE IF EXISTS locking_items CASCADE;
CREATE TABLE locking_items (id bigserial PRIMARY KEY, value int, version int);

DROP TABLE IF EXISTS alive_beans CASCADE;
CREATE TABLE alive_beans (id bigserial PRIMARY KEY, name varchar(255));

DROP TABLE IF EXISTS bottomless_boxes CASCADE;
CREATE TABLE bottomless_boxes (id bigserial PRIMARY KEY, title varchar(255));
INSERT INTO bottomless_boxes (title) VALUES
('Big Green Box'),
('Red Box');

DROP TABLE IF EXISTS things CASCADE;
CREATE TABLE things (id bigserial, title varchar(255), box_id bigint REFERENCES bottomless_boxes (id));
INSERT INTO things (title, box_id) VALUES
('toy', 1),
('ball', 1),
('pen', 2),
('pencil', null);

DROP TABLE IF EXISTS simple_entities CASCADE;
CREATE TABLE simple_entities (id bigserial PRIMARY KEY, name varchar(255));
INSERT INTO simple_entities (name) VALUES ('item 1');

COMMIT;