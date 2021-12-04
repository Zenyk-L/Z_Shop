DROP DATABASE IF EXISTS z_shop;

CREATE DATABASE IF NOT EXISTS z_shop CHAR SET UTF8;

USE z_shop;

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id         BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    email      VARCHAR(50)        NOT NULL UNIQUE,
    first_name VARCHAR(25)        NOT NULL,
    last_name  VARCHAR(25)        NOT NULL,
    password   VARCHAR(50)        NOT NULL,
    role       VARCHAR(20)        NOT NULL DEFAULT 'USER',
    amount     DECIMAL(9, 2)               DEFAULT 0.00,
    blocked    BOOLEAN                     DEFAULT FALSE
);



DROP TABLE IF EXISTS language;
CREATE TABLE language
(
    id         BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    short_name VARCHAR(30)        NOT NULL UNIQUE,
    full_name  VARCHAR(30)        NOT NULL UNIQUE,
    deleted    BOOLEAN DEFAULT FALSE,
    UNIQUE (short_name)
);

ALTER TABLE language
    DROP PRIMARY KEY,
    ADD UNIQUE KEY short_name;

DROP TABLE IF EXISTS category;
CREATE TABLE category
(
    id      BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    deleted BOOLEAN DEFAULT FALSE
);

DROP TABLE IF EXISTS category_description;
CREATE TABLE category_description
(
    id            BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    category_id   BIGINT             NOT NULL,
    language_id   VARCHAR(30)        NOT NULL,
    category_name VARCHAR(30)        NOT NULL UNIQUE,
    deleted       BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (language_id) REFERENCES language (short_name) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE
);



DROP TABLE IF EXISTS product;
CREATE TABLE product
(
    id          BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name        VARCHAR(30)        NOT NULL,
    image       VARCHAR(30),
    category_id BIGINT,
    quantity    INT UNSIGNED,
    description TEXT               NOT NULL,
    color       VARCHAR(20)        NOT NULL,
    scale       VARCHAR(20)        NOT NULL,
    price       DECIMAL(9, 2) DEFAULT 0.00,
    adding_date TIMESTAMP     default CURRENT_TIMESTAMP,
    deleted     BOOLEAN       DEFAULT FALSE,
    FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE SET NULL
);

DROP TABLE IF EXISTS bucket;
CREATE TABLE bucket
(
    id            BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id       BIGINT             NOT NULL,
    product_id    BIGINT             NOT NULL,
    quantity      INT    UNSIGNED,
    purchase_date TIMESTAMP default CURRENT_TIMESTAMP,
    deleted       BOOLEAN   DEFAULT FALSE,
    status        VARCHAR(20)        NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);

INSERT INTO language (short_name, full_name) VALUE ('en', 'English'), ('ua', 'Українська'), ('pl', 'Polska');
INSERT INTO category() VALUE (), ();
INSERT INTO category_description(category_id, language_id, category_name) value (17,'en', 'Notebook'), (17,'ua', 'Ноутбук'), (17,'pl', 'Laptop'); #(1, 'en', 'Phone'), (2, 'en', 'Car'), (1, 'ua', 'Телефон'), (1, 'pl', 'Telefon'), (2, 'ua', 'Авто'), (2, 'pl', 'Auto');
INSERT INTO product(name, image, category_id, quantity, description, color, scale, price)
    VALUE ('Nokia', 'nokia.jpg', 1, 15, 'button phone', 'black', 'small', 9.99),
    ('IPhone 11', 'IPhone11.jpg', 1, 10, 'sensor phone', 'Red', 'middle', 999.99),
    ('IPhone 11', 'IPhone11.jpg', 1, 15, 'sensor phone', 'Silver', 'middle', 999.99),
    ('IPhone 11', 'IPhone11.jpg', 1, 25, 'sensor phone', 'Grey', 'middle', 999.99),
    ('IPhone 11', 'IPhone11.jpg', 1, 2, 'sensor phone, damaged', 'Red', 'middle', 599.99),
    ('IPhone 13', 'IPhone13.jpg', 1, 11, 'sensor phone', 'Silver', 'middle', 1499.99),
    ('IPhone 13', 'IPhone13.jpg', 1, 13, 'sensor phone', 'Grey', 'middle', 1499.99),
    ('IPhone 13', 'IPhone13.jpg', 1, 50, 'sensor phone', 'Pink', 'middle', 1499.99),
    ('Nokia 1.4', 'Nokia_1.4.jpg', 1, 5, 'sensor phone', 'Blue', 'middle', 199.99),
    ('Nokia 1.4', 'Nokia_1.4.jpg', 1, 5, 'sensor phone', 'Black', 'middle', 199.99),
    ('Nokia 1.4', 'Nokia_1.4.jpg', 1, 5, 'sensor phone', 'Grey', 'middle', 199.99),
    ('Nokia 10', 'Nokia_10.jpg', 1, 5, 'button phone', 'Red', 'small', 19.99),
    ('Nokia 10', 'Nokia_10.jpg', 1, 5, 'button phone', 'Blue', 'small', 29.99),
    ('Nokia 10', 'Nokia_10.jpg', 1, 5, 'button phone', 'Silver', 'small', 39.99),
    ('Nokia 10', 'Nokia_10.jpg', 1, 5, 'button phone', 'Black', 'small', 49.99),
    ('Nokia 10', 'Nokia_10.jpg', 1, 5, 'button phone', 'Grey', 'small', 59.99),
    ('Lenovo IdeaPad3', 'Lenovo_IdeaPad3.jpg', 17, 1, 'Screen 15', 'Grey', 'big', 500.00),
    ('Lenovo IdeaPad3', 'Lenovo_IdeaPad3.jpg', 17, 1, 'Screen 15', 'Black', 'big', 505.00),
    ('Lenovo IdeaPad3', 'Lenovo_IdeaPad3.jpg', 17, 1, 'Screen 15', 'Silver', 'big', 520.99),
    ('Lenovo IdeaPad3', 'Lenovo_IdeaPad3.jpg', 17, 1, 'Screen 15', 'Grey', 'big', 500.00),
    ('HP 15s', 'HP_15s.jpg', 17, 1, 'Screen 15', 'Grey', 'big', 600.00),
    ('HP 15s', 'HP_15s.jpg', 17, 1, 'Screen 15', 'Black', 'big', 601.00),
    ('HP 15s', 'HP_15s.jpg', 17, 1, 'Screen 15', 'Silver', 'big', 602.00),
    ('Asus X515', 'Asus_X515.jpg', 17, 1, 'Screen 15', 'Grey', 'big', 610.00),
    ('Asus X515', 'Asus_X515.jpg', 17, 1, 'Screen 15', 'Black', 'big', 611.00),
    ('Asus X515', 'Asus_X515.jpg', 17, 1, 'Screen 15', 'Silver', 'big', 612.00),
    ('Dell Vostro15', 'Dell_Vostro15.jpg', 17, 1, 'Screen 15', 'Grey', 'big', 615.00),
    ('Dell Vostro15', 'Dell_Vostro15.jpg', 17, 1, 'Screen 15', 'Black', 'big', 616.00),
    ('Dell Vostro15', 'Dell_Vostro15.jpg', 17, 1, 'Screen 15', 'Silver', 'big', 617.00);






#     ('Xiaomi', 'xiaomi.jpg', 1, 20, 'sensor phone', 'blue', 'middle', 199.99);

INSERT INTO product(name, category_id, description, color, scale, price) value ('a', 1, 'description', 'blue', 'middle', 199.99), ('b', 1, 'description', 'blue', 'middle', 199.99), ('c', 1, 'description', 'blue', 'middle', 199.99)
    , ('d', 1, 'description', 'blue', 'middle', 199.99), ('а', 1, 'description', 'blue', 'middle', 199.99), ('б', 1, 'description', 'blue', 'middle', 199.99), ('в', 1, 'description', 'blue', 'middle', 199.99), ('г', 1, 'description', 'blue', 'middle', 199.99);

INSERT INTO user(email, first_name, last_name, password, role, amount) VALUE ('user@mail.com', 'user1', 'One User', 'user', 'USER', 999.99), ('admin@mail.com', 'Admin1', 'ONE ADMIN', 'admin', 'ADMIN', 0.0);

select *
from z_shop.user;

select *
from product;

delete
from bucket
where user_id = 1;

select *
from z_shop.language;

select *
from category_description;


select *
from category;

select *
from z_shop.user;

select *
from bucket;
# where user_id = 4 and product_id = 3;


INSERT INTO category value ();

INSERT INTO category_description (category_id, language_id, category_name) VALUE (23, 'en', 'Cat');

SELECT c.id, c.name, cd.language_id, cd.category_name
FROM category c
         RIGHT JOIN category_description cd on c.id = cd.category_id
WHERE c.deleted = false
  AND cd.deleted = false;
SELECT c.id, c.name, cd.language_id, cd.category_name
FROM category c
         LEFT JOIN category_description cd on c.id = cd.category_id
WHERE c.deleted = false;

select p.name,
       p.image,
       p.category_id,
       cd.category_name,
       l.short_name,
       l.full_name,
       p.quantity,
       p.description,
       p.color,
       p.scale,
       p.price,
       p.adding_date,
       p.deleted
from product p
         join category c on p.category_id = c.id
         join category_description cd on cd.category_id = c.id
         join language l on cd.language_id = l.id;

select p.id,
       p.name,
       p.image,
       c.name,
       p.quantity,
       p.description,
       p.color,
       p.scale,
       p.price,
       p.adding_date,
       p.deleted
from product p
         join category c on p.category_id = c.id;


# INSERT INTO user(name, last_name, email, role, amount)
#     value ('user1', 'USER1', 'user1@gmail.com', 'USER', 100.00);
#
# INSERT INTO user(name, last_name, email, role)
#     value ('user2', 'USER2', 'user2@gmail.com', 'USER');
#
# select *
# from user;
#
# INSERT INTO product(name, category, description, color, scale, price)
#     value ('product1', 'category1', 'good product', 'white', 'big', 5);
#
# select *
# from z_shop.products;
#
# UPDATE product
# SET product.image = 'MEDICINE.jpg'
# WHERE id % 5 = 0;
# DELETE
# FROM product
# where id = 25;
#
# INSERT INTO bucket(user_id, product_id)
#     VALUE (1, 1);
#
# select *
# from bucket;
#
# SELECT *
# FROM z_shop.product
# WHERE deleted = 0
#   AND id = ?;


# INSERT INTO category(name_en, name_uk)
#     value ('Car', 'Авто'), ('Building', 'Будинок'), ('Phone', 'Телефон');
#
# SELECT *
# FROM category;
#
# INSERT INTO product(name, image, category_id, quantity, description_en, description_uk, color_en, color_uk, scale,
#                     price)
#     value ('Audi', 'audi.jpg', 1, 10, 'German car', 'Німецьке авто', 'white', 'білий', 'big', 5999.99),
#     ('Audi', 'audi.jpg', 1, 10, 'German car', 'Німецьке авто', 'white', 'білий', 'big', 5999.99),
#     ('BMW', 'bmw.jpg', 1, 5, 'German car', 'Німецьке авто', 'red', 'червоний', 'small', 2999.99),
#     ('Tower', 'tower.jpg', 2, 10, 'for offices', 'для офісів', 'white', 'білий', 'big', 99999.99),
#     ('Liberty', 'liberty.jpg', 2, 1, 'National Monument', 'Національний монумент', 'green', 'зелена', 'big', 999999.99),
#     ('Nokia', 'nokia.jpg', 3, 15, 'button phone', 'кнопочний телефон', 'black', 'чорний', 'small', 9.99),
#     ('Xiaomi', 'xiaomi.jpg', 3, 20, 'sensor phone', 'сенсорний телефон', 'blue', 'синій', 'middle', 199.99);
#
# SELECT *
# FROM z_shop.product;
#
# # select full products data from db
# select p.name,
#        p.image,
#        c.name_en,
#        c.name_uk,
#        p.quantity,
#        p.description_en,
#        p.description_uk,
#        p.color_en,
#        p.color_uk,
#        p.scale,
#        p.price,
#        p.adding_date,
#        p.deleted
# from product p
#          join category c on p.category_id = c.id;
#
# # select locale product data from db
# SET @locate = 'uk';
# select p.name,
#        p.image,
#        c.name_en        as name,
#        p.quantity,
#        p.description_en as description,
#        p.color_en       as color,
#        p.scale,
#        p.price,
#        p.adding_date,
#        p.deleted
# from product p
#          join category c on p.category_id = c.id;
#
#
# INSERT INTO category(name_en, name_uk)
#     value ('myCar', 'моєАвто');
#
# SET @name_eng = 'myCar';
#
#
# insert into z_shop.product (name, image, category_id, quantity, description_en, description_uk, color_en, color_uk,
#                             scale, price)
#     value ('LAST_LAST_CAR', 'bmw.jpg', (SELECT id from category where name_en = @name_eng), 5, 'German car',
#            'Німецьке авто', 'red',
#            'червоний', 'small', 2999.99);
#
# SELECT *
# from category;
# DROP PROCEDURE addNewCategory;
# CREATE PROCEDURE addNewCategory(IN text_en VARCHAR(30), IN text_uk VARCHAR(30))
# BEGIN
#     IF
#         (SELECT id
#          FROM category
#          WHERE name_en = text_en) is null
#     then
#         INSERT INTO category(name_en, name_uk) value (text_en, text_uk);
#     END IF;
# END;
#
# SET @name_eng = 'myCar9' , @name_ukr = 'МоєАвто9';
# CALL addNewCategory(@name_eng, @name_ukr);
#
#
# CREATE PROCEDURE addNewCategory(IN text_en VARCHAR(30), IN text_uk VARCHAR(30))
# BEGIN
#     IF (SELECT id FROM z_shop.category WHERE name_en = text_en) is null then
#         INSERT INTO z_shop.category(name_en, name_uk) value (text_en, text_uk);
#     END IF;
# END;