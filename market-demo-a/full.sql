BEGIN;

SET search_path TO hibernate;

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

DROP TABLE IF EXISTS category CASCADE;
CREATE TABLE category (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO category (name) VALUES
('Stationery'),
('Devices'),
('Household products'),
('Jewelry'),
('Flowers and Plants'),
('Medication');

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
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price int, company_id bigint, category_id bigint, FOREIGN KEY (company_id) REFERENCES company (id), FOREIGN KEY (category_id) REFERENCES category (id));
INSERT INTO products (title, price, company_id, category_id) VALUES
('box', 10, 8, 3),
('sphere', 20, 7, 1),
('maul', 100, 5, 3),
('door', 50, 5, 3),
('camera', 5000, 1, 2),
('pen', 15, 7, 1),
('phone', 20000, 1, 2),
('earphone', 1000, 4, 2),
('ring', 500, 6, 4),
('paper', 120, 7, 1),
('medicine', 200, 2, 6),
('note', 40, 7, 1),
('bottle', 5, 2, 3),
('lamp', 60, 9, 3),
('flower', 70, 3, 5),
('comb', 30, 9, 3),
('rubber', 9, 7, 1),
('tape', 45, 6, 3),
('toy', 600, 8, 3),
('board', 100, 5, 3),
('garland', 350, 5, 3);

COMMIT;