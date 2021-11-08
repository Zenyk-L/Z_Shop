DROP DATABASE  IF EXISTS z_shop;

CREATE DATABASE IF NOT EXISTS z_shop;

USE z_shop;

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id        BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    email     VARCHAR(50)        NOT NULL UNIQUE,
    name      VARCHAR(25)        NOT NULL,
    last_name VARCHAR(25)        NOT NULL,
    password  VARCHAR(25)        NOT NULL,
    role VARCHAR(20)        NOT NULL,
    amount    DOUBLE(9, 2) DEFAULT 0.00
);

DROP TABLE IF EXISTS products;
CREATE TABLE products
(
    id          BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name        VARCHAR(25)        NOT NULL,
    image       BLOB,
    category    VARCHAR(25)        NOT NULL,
    description TEXT               NOT NULL,
    color       VARCHAR(20)        NOT NULL,
    scale       VARCHAR(20)        NOT NULL,
    price       DOUBLE(9, 2) DEFAULT 0.00,
    adding_date TIMESTAMP    default CURRENT_TIMESTAMP,
    deleted     BOOLEAN      DEFAULT FALSE
);

DROP TABLE IF EXISTS buckets;
CREATE TABLE buckets
(
    id            BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id       BIGINT             NOT NULL,
    product_id    BIGINT             NOT NULL,
    purchase_date TIMESTAMP default CURRENT_TIMESTAMP,
    deleted       BOOLEAN   DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

INSERT INTO users(name, last_name, email, role, amount)
    value ('user1', 'USER1', 'user1@gmail.com', 'USER', 100.00);

INSERT INTO users(name, last_name, email, role)
    value ('user2', 'USER2', 'user2@gmail.com', 'USER');

select *
from users;

INSERT INTO products(name, category, description, color, scale, price)
    value ('product1', 'category1', 'good product', 'white', 'big', 5);

select *
from products;

INSERT INTO buckets(user_id, product_id)
    VALUE (1, 1);

select *
from buckets;

SELECT * FROM z_shop.products WHERE deleted = 0 AND id =?