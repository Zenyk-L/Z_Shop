package com.z.shop.service;

import com.z.shop.entity.Product;
import com.z.shop.service.impl.ProductServiceImpl;
import com.z.shop.utils.DBManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

@RunWith(JUnit4.class)
public class ProductServiceTest {
    private static final Logger LOGGER = LogManager.getLogger(ProductServiceTest.class);
    private static DBManager dbManager;
    private static ProductService productService;


    private static String SQL_DROP_DB = "DROP DATABASE If EXISTS z_shop_test;";
    private static String SQL_CRETE_DB = "CREATE DATABASE IF NOT EXISTS z_shop_test CHAR SET UTF8;";
    private static String SQL_USE_DB = "USE z_shop_test;";
    private static String SQL_CRETE_TABLE_LANG = "CREATE TABLE language\n" +
            "(\n" +
            "    id         BIGINT  PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
            "    short_name VARCHAR(30)         NOT NULL UNIQUE,\n" +
            "    full_name  VARCHAR(30)        NOT NULL UNIQUE,\n" +
            "    deleted    BOOLEAN DEFAULT FALSE,\n" +
            "    UNIQUE(short_name)\n" +
            ");";

    private static String SQL_CRETE_TABLE_CATEGORY = "CREATE TABLE category\n" +
            "(\n" +
            "    id      BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
            "    deleted BOOLEAN DEFAULT FALSE\n" +
            ");";

    private static String SQL_CRETE_TABLE_CATEGORY_DESCR = "CREATE TABLE category_description\n" +
            "(\n" +
            "    id            BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
            "    category_id   BIGINT             NOT NULL,\n" +
            "    language_id   VARCHAR(30)             NOT NULL,\n" +
            "    category_name VARCHAR(30)        NOT NULL UNIQUE,\n" +
            "    deleted       BOOLEAN DEFAULT FALSE,\n" +
            "    FOREIGN KEY (language_id) REFERENCES language (short_name)  ON DELETE CASCADE,\n" +
            "    FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE\n" +
            ");";

    private static String SQL_CRETE_TABLE_PRODUCT = "CREATE TABLE product\n" +
            "(\n" +
            "    id          BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
            "    name        VARCHAR(30)        NOT NULL,\n" +
            "    image       VARCHAR(30),\n" +
            "    category_id BIGINT             ,\n" +
            "    quantity    INT UNSIGNED,\n" +
            "    description TEXT               NOT NULL,\n" +
            "    color       VARCHAR(20)        NOT NULL,\n" +
            "    scale       VARCHAR(20)        NOT NULL,\n" +
            "    price       DECIMAL(9, 2) DEFAULT 0.00,\n" +
            "    adding_date TIMESTAMP     default CURRENT_TIMESTAMP,\n" +
            "    deleted     BOOLEAN       DEFAULT FALSE,\n" +
            "    FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE SET NULL\n" +
            ");";



    private static String SQL_BASIC_FILL_LANG = " INSERT INTO language (short_name, full_name) VALUE ('en','English'),('ua', 'Українська'), ('pl', 'Polska');";
    private static String SQL_BASIC_FILL_CAT = "INSERT INTO category() VALUE (), ();";
    private static String SQL_BASIC_FILL_CAT_DESCR = " INSERT INTO category_description(category_id, language_id, category_name) value (1, 'en', 'Phone'), (2, 'en', 'Car'), (1, 'ua', 'Телефон'), (1, 'pl', 'Telefon'), (2, 'ua', 'Авто'), (2, 'pl', 'Auto');";
    private static String SQL_BASIC_FILL_PRODUCTS = "INSERT INTO product(name, image, category_id, quantity, description, color, scale, price)\n" +
            "    VALUE ('Nokia', 'nokia.jpg', 1, 15, 'button phone', 'black', 'small', 9.99 ),\n" +
            "    ('AUDI', 'audi.jpg', 2, 5, 'sedan', 'blue', 'middle', 9999.99);";



    @BeforeClass
    public static void setupClass() {
        dbManager = DBManager.getInstance();
        dbManager.setSqlConnectionUrl("jdbc:mysql://localhost:3306/z_shop_test?user=root&password=123456&serverTimezone=UTC&useSSL=false");
        productService = ProductServiceImpl.getProductService();
    }

    @Before
    public void before() {
        try (Connection connection = dbManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL_DROP_DB);
            statement.executeUpdate(SQL_CRETE_DB);
            statement.executeUpdate(SQL_USE_DB);
            statement.executeUpdate(SQL_CRETE_TABLE_LANG);
            statement.executeUpdate(SQL_CRETE_TABLE_CATEGORY);
            statement.executeUpdate(SQL_CRETE_TABLE_CATEGORY_DESCR);
            statement.executeUpdate(SQL_CRETE_TABLE_PRODUCT);
            statement.executeUpdate(SQL_BASIC_FILL_LANG);
            statement.executeUpdate(SQL_BASIC_FILL_CAT);
            statement.executeUpdate(SQL_BASIC_FILL_CAT_DESCR);
            statement.executeUpdate(SQL_BASIC_FILL_PRODUCTS);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Test
    public void createProductTest() {
        Product product = new Product();
        product.setName("Xiaomi");
        product.setImage("xiaomi.jpg");
        product.getCategory().setId(1);
        product.setQuantity(1);
        product.setDescription("sensor phone");
        product.setColor("silver");
        product.setScale("middle");
        product.setPrice(199.99);
        product.setAddingDate(new Date());

        productService.create(product);
        product.setId(3);

        Product productFromDB = productService.read(3);

        Assert.assertEquals(product.getId(), productFromDB.getId());
        Assert.assertEquals(product.getName(), productFromDB.getName());
        Assert.assertEquals(product.getImage(), productFromDB.getImage());
        Assert.assertEquals(product.getCategory().getId(), productFromDB.getCategory().getId());
        Assert.assertEquals(product.getQuantity(), productFromDB.getQuantity());
        Assert.assertEquals(product.getDescription(), productFromDB.getDescription());
        Assert.assertEquals(product.getColor(), productFromDB.getColor());
        Assert.assertEquals(product.getScale(), productFromDB.getScale());
        Assert.assertEquals(product.getPrice(), productFromDB.getPrice());
        Assert.assertEquals(product.isDeleted(), productFromDB.isDeleted());

    }


    @Test
    public void readProductByIdTest() {
        Product product = new Product();
        product.setName("Xiaomi");
        product.setImage("xiaomi.jpg");
        product.getCategory().setId(1);
        product.setQuantity(1);
        product.setDescription("sensor phone");
        product.setColor("silver");
        product.setScale("middle");
        product.setPrice(199.99);
        product.setAddingDate(new Date());
        productService.create(product);


        Product productFromDB = productService.read(product.getId());

        Assert.assertEquals(product.getId(), productFromDB.getId());
        Assert.assertEquals(product.getName(), productFromDB.getName());
        Assert.assertEquals(product.getImage(), productFromDB.getImage());
        Assert.assertEquals(product.getCategory().getId(), productFromDB.getCategory().getId());
        Assert.assertEquals(product.getQuantity(), productFromDB.getQuantity());
        Assert.assertEquals(product.getDescription(), productFromDB.getDescription());
        Assert.assertEquals(product.getColor(), productFromDB.getColor());
        Assert.assertEquals(product.getScale(), productFromDB.getScale());
        Assert.assertEquals(product.getPrice(), productFromDB.getPrice());
        Assert.assertEquals(product.isDeleted(), productFromDB.isDeleted());

    }

    @Test
    public void deleteProductTest() {

        Product product = productService.read(2);
        productService.delete(2);
        Product productFromDB = productService.read(2);

        Assert.assertEquals(product.getId(), productFromDB.getId());
        Assert.assertEquals(product.getName(), productFromDB.getName());
        Assert.assertEquals(product.getImage(), productFromDB.getImage());
        Assert.assertEquals(product.getCategory().getId(), productFromDB.getCategory().getId());
        Assert.assertEquals(product.getQuantity(), productFromDB.getQuantity());
        Assert.assertEquals(product.getDescription(), productFromDB.getDescription());
        Assert.assertEquals(product.getColor(), productFromDB.getColor());
        Assert.assertEquals(product.getScale(), productFromDB.getScale());
        Assert.assertEquals(product.getPrice(), productFromDB.getPrice());
        Assert.assertNotEquals(product.isDeleted(), productFromDB.isDeleted());

    }

    @Test
    public void updateProductTest() {

        Product product = new Product();
        product.setId(1);
        product.setName("Xiaomi");
        product.setImage("xiaomi.jpg");
        product.getCategory().setId(1);
        product.setQuantity(1);
        product.setDescription("sensor phone");
        product.setColor("silver");
        product.setScale("middle");
        product.setPrice(199.99);
        product.setAddingDate(new Date());
        productService.update(product);


        Product productFromDB = productService.read(product.getId());

        Assert.assertEquals(product.getId(), productFromDB.getId());
        Assert.assertEquals(product.getName(), productFromDB.getName());
        Assert.assertEquals(product.getImage(), productFromDB.getImage());
        Assert.assertEquals(product.getCategory().getId(), productFromDB.getCategory().getId());
        Assert.assertEquals(product.getQuantity(), productFromDB.getQuantity());
        Assert.assertEquals(product.getDescription(), productFromDB.getDescription());
        Assert.assertEquals(product.getColor(), productFromDB.getColor());
        Assert.assertEquals(product.getScale(), productFromDB.getScale());
        Assert.assertEquals(product.getPrice(), productFromDB.getPrice());
        Assert.assertEquals(product.isDeleted(), productFromDB.isDeleted());

    }


    @Test
    public void readAllProductTest() {
        Product product = new Product();
        product.setName("Xiaomi");
        product.setImage("xiaomi.jpg");
        product.getCategory().setId(1);
        product.setQuantity(1);
        product.setDescription("sensor phone");
        product.setColor("silver");
        product.setScale("middle");
        product.setPrice(199.99);
        product.setAddingDate(new Date());
        productService.create(product);

        List<Product> productsFromDB = productService.readAll();
        Product productFromDB = productsFromDB.get(2);

        Assert.assertEquals(product.getId(), productFromDB.getId());
        Assert.assertEquals(product.getName(), productFromDB.getName());
        Assert.assertEquals(product.getImage(), productFromDB.getImage());
        Assert.assertEquals(product.getCategory().getId(), productFromDB.getCategory().getId());
        Assert.assertEquals(product.getQuantity(), productFromDB.getQuantity());
        Assert.assertEquals(product.getDescription(), productFromDB.getDescription());
        Assert.assertEquals(product.getColor(), productFromDB.getColor());
        Assert.assertEquals(product.getScale(), productFromDB.getScale());
        Assert.assertEquals(product.getPrice(), productFromDB.getPrice());
        Assert.assertEquals(product.isDeleted(), productFromDB.isDeleted());

    }

}
