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

COMMIT;