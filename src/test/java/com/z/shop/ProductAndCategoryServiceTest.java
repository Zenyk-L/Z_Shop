package com.z.shop;

import com.z.shop.entity.Category;
import com.z.shop.entity.Product;
import com.z.shop.service.CategoryService;
import com.z.shop.service.ProductService;
import com.z.shop.service.impl.CategoryServiceImpl;
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
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class ProductAndCategoryServiceTest {
    private static final Logger LOGGER = LogManager.getLogger(ProductAndCategoryServiceTest.class);
    private static DBManager dbManager;
    private static ProductService productService;
    private static CategoryService categoryService;

    private static String SQL_DROP_DB = "DROP DATABASE If EXISTS z_shop_test;";
    private static String SQL_CRETE_DB = "CREATE DATABASE IF NOT EXISTS z_shop_test CHAR SET UTF8;";
    private static String SQL_USE_DB = "USE z_shop_test;";
    private static String SQL_CRETE_TABLE_LANG = "CREATE TABLE language\n" +
            "(\n" +
            "    id         BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
            "    short_name VARCHAR(30)        NOT NULL UNIQUE,\n" +
            "    full_name  VARCHAR(30)        NOT NULL UNIQUE,\n" +
            "    deleted    BOOLEAN DEFAULT FALSE\n" +
            ");";

    private static String SQL_CRETE_TABLE_CATEGORY = "CREATE TABLE category\n" +
            "(\n" +
            "    id      BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
            "    name    VARCHAR(30)        NOT NULL UNIQUE,\n" +
            "    deleted BOOLEAN DEFAULT FALSE\n" +
            ");";

    private static String SQL_CRETE_TABLE_CATEGORY_DESCR = "CREATE TABLE category_description\n" +
            "(\n" +
            "    id            BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
            "    category_id   BIGINT             NOT NULL,\n" +
            "    language_id   BIGINT             NOT NULL,\n" +
            "    category_name VARCHAR(30)        NOT NULL UNIQUE,\n" +
            "    deleted       BOOLEAN DEFAULT FALSE,\n" +
            "    FOREIGN KEY (language_id) REFERENCES language (id) ON DELETE CASCADE,\n" +
            "    FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE\n" +
            ");";

    private static String SQL_CRETE_TABLE_PRODUCT = "CREATE TABLE product\n" +
            "(\n" +
            "    id          BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
            "    name        VARCHAR(30)        NOT NULL,\n" +
            "    image       VARCHAR(30),\n" +
            "    category_id BIGINT             NOT NULL,\n" +
            "    quantity    INT UNSIGNED,\n" +
            "    description TEXT               NOT NULL,\n" +
            "    color       VARCHAR(20)        NOT NULL,\n" +
            "    scale       VARCHAR(20)        NOT NULL,\n" +
            "    price       DECIMAL(9, 2) DEFAULT 0.00,\n" +
            "    adding_date TIMESTAMP     default CURRENT_TIMESTAMP,\n" +
            "    deleted     BOOLEAN       DEFAULT FALSE,\n" +
            "    FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE\n" +
            ");";
    private static String SQL_BASIC_FILL_LANG = " INSERT INTO language (short_name, full_name) VALUE ('ua', 'Українська'), ('pl', 'Polska');";
    private static String SQL_BASIC_FILL_CAT = "INSERT INTO category(name) VALUE ('Phone'), ('Car'), ('Plane');";
    private static String SQL_BASIC_FILL_CAT_DESCR = " INSERT INTO category_description(category_id, language_id, category_name) value (1, 1, 'Телефон'), (1, 2, 'Telefon'), (2, 1, 'Авто'), (1, 2, 'Auto');";
    private static String SQL_BASIC_FILL_PRODUCTS = "INSERT INTO product(name, image, category_id, quantity, description, color, scale, price)\n" +
            "    VALUE ('Nokia', 'nokia.jpg', 1, 15, 'button phone', 'black', 'small', 9.99 ),\n" +
            "    ('AUDI', 'audi.jpg', 2, 5, 'sedan', 'blue', 'middle', 9999.99);";


    @BeforeClass
    public static void setupClass() {
        dbManager = DBManager.getInstance();
        dbManager.setSqlConnectionUrl("jdbc:mysql://localhost:3306/z_shop_test?user=root&password=123456&serverTimezone=UTC&useSSL=false");
        productService = ProductServiceImpl.getProductService();
        categoryService = CategoryServiceImpl.getCategoryServiceImpl();
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
    public void createProductTest(){
        Product product = new Product(0,"Xiaomi", "xiaomi.jpg", "Phone", 5, "sensor phone", "silver", "middle", 199.99, new Date(new java.util.Date().getTime()), false);

        productService.create(product);
        product.setId(3);

        Product productFromDB = productService.read(3);

        Assert.assertEquals(product.getId(),productFromDB.getId());
        Assert.assertEquals(product.getName(),productFromDB.getName());
        Assert.assertEquals(product.getImage(),productFromDB.getImage());
        Assert.assertEquals(product.getCategory() ,productFromDB.getCategory());
        Assert.assertEquals(product.getQuantity(),productFromDB.getQuantity());
        Assert.assertEquals(product.getDescription(),productFromDB.getDescription());
        Assert.assertEquals(product.getColor(),productFromDB.getColor());
        Assert.assertEquals(product.getScale(),productFromDB.getScale());
        Assert.assertEquals(product.getPrice(),productFromDB.getPrice());
        Assert.assertEquals(product.isDeleted(),productFromDB.isDeleted());

    }


    @Test
    public void readProductByIdTest(){
        Product product1 = new Product(1,"Nokia", "nokia.jpg", "Phone", 15, "button phone", "black", "small", 9.99, new Date(new java.util.Date().getTime()), false);
        Product product2 = new Product(2,"AUDI", "audi.jpg", "Car", 5, "sedan", "blue", "middle", 9999.99,  new Date(new java.util.Date().getTime()), false);

        List<Product> expectedProducts = new ArrayList();
        expectedProducts.add(product1);
        expectedProducts.add(product2);

        List<Product> gettedFromBDProducts = productService.readAll();

        Product productFromDB = productService.read(1);

        Assert.assertEquals(product1.getId(),productFromDB.getId());
        Assert.assertEquals(product1.getName(),productFromDB.getName());
        Assert.assertEquals(product1.getImage(),productFromDB.getImage());
        Assert.assertEquals(product1.getCategory() ,productFromDB.getCategory());
        Assert.assertEquals(product1.getQuantity(),productFromDB.getQuantity());
        Assert.assertEquals(product1.getDescription(),productFromDB.getDescription());
        Assert.assertEquals(product1.getColor(),productFromDB.getColor());
        Assert.assertEquals(product1.getScale(),productFromDB.getScale());
        Assert.assertEquals(product1.getPrice(),productFromDB.getPrice());
        Assert.assertEquals(product1.isDeleted(),productFromDB.isDeleted());

        productFromDB = productService.read(2);
        Assert.assertEquals(product2.getId(),productFromDB.getId());
        Assert.assertEquals(product2.getName(),productFromDB.getName());
        Assert.assertEquals(product2.getImage(),productFromDB.getImage());
        Assert.assertEquals(product2.getCategory() ,productFromDB.getCategory());
        Assert.assertEquals(product2.getQuantity(),productFromDB.getQuantity());
        Assert.assertEquals(product2.getDescription(),productFromDB.getDescription());
        Assert.assertEquals(product2.getColor(),productFromDB.getColor());
        Assert.assertEquals(product2.getScale(),productFromDB.getScale());
        Assert.assertEquals(product2.getPrice(),productFromDB.getPrice());
        Assert.assertEquals(product2.isDeleted(),productFromDB.isDeleted());
    }

    @Test
    public void deleteProductTest(){

        Product product = productService.read(2);
        productService.delete(2);
        Product productFromDB = productService.read(2);

        Assert.assertEquals(product.getId(),productFromDB.getId());
        Assert.assertEquals(product.getName(),productFromDB.getName());
        Assert.assertEquals(product.getImage(),productFromDB.getImage());
        Assert.assertEquals(product.getCategory() ,productFromDB.getCategory());
        Assert.assertEquals(product.getQuantity(),productFromDB.getQuantity());
        Assert.assertEquals(product.getDescription(),productFromDB.getDescription());
        Assert.assertEquals(product.getColor(),productFromDB.getColor());
        Assert.assertEquals(product.getScale(),productFromDB.getScale());
        Assert.assertEquals(product.getPrice(),productFromDB.getPrice());
        Assert.assertNotEquals(product.isDeleted(),productFromDB.isDeleted());

    }

    @Test
    public void updateProductTest(){
        Product product = new Product(1,"BMV", "bmv.jpg", "Car", 1, "sport car", "red", "big", 9999.99, new Date(new java.util.Date().getTime()), false);
        productService.update(product);

        Product productFromDB = productService.read(1);

        Assert.assertEquals(product.getId(),productFromDB.getId());
        Assert.assertEquals(product.getName(),productFromDB.getName());
        Assert.assertEquals(product.getImage(),productFromDB.getImage());
        Assert.assertEquals(product.getCategory() ,productFromDB.getCategory());
        Assert.assertEquals(product.getQuantity(),productFromDB.getQuantity());
        Assert.assertEquals(product.getDescription(),productFromDB.getDescription());
        Assert.assertEquals(product.getColor(),productFromDB.getColor());
        Assert.assertEquals(product.getScale(),productFromDB.getScale());
        Assert.assertEquals(product.getPrice(),productFromDB.getPrice());
        Assert.assertEquals(product.isDeleted(),productFromDB.isDeleted());

    }


    @Test
    public void readAllProductTest(){
        Product product1 = new Product(1,"Nokia", "nokia.jpg", "Phone", 15, "button phone", "black", "small", 9.99, new Date(new java.util.Date().getTime()), false);
        Product product2 = new Product(2,"AUDI", "audi.jpg", "Car", 5, "sedan", "blue", "middle", 9999.99,  new Date(new java.util.Date().getTime()), false);

        List<Product> expectedProducts = new ArrayList();
        expectedProducts.add(product1);
        expectedProducts.add(product2);

        List<Product> gettedFromBDProducts = productService.readAll();

        Product productFromDB = gettedFromBDProducts.get(0);

        Assert.assertEquals(product1.getId(),productFromDB.getId());
        Assert.assertEquals(product1.getName(),productFromDB.getName());
        Assert.assertEquals(product1.getImage(),productFromDB.getImage());
        Assert.assertEquals(product1.getCategory() ,productFromDB.getCategory());
        Assert.assertEquals(product1.getQuantity(),productFromDB.getQuantity());
        Assert.assertEquals(product1.getDescription(),productFromDB.getDescription());
        Assert.assertEquals(product1.getColor(),productFromDB.getColor());
        Assert.assertEquals(product1.getScale(),productFromDB.getScale());
        Assert.assertEquals(product1.getPrice(),productFromDB.getPrice());
        Assert.assertEquals(product1.isDeleted(),productFromDB.isDeleted());

    }

    @Test
    public void createCategoryTest() {
        Category category = new Category();
        category.setName("Shoes");
        categoryService.create(category);


        Category categoryFromDB = categoryService.read(category.getId());
        System.out.println(category);
        System.out.println(categoryFromDB);
        Assert.assertEquals(category.getId(), categoryFromDB.getId());
        Assert.assertEquals(category.getName(), categoryFromDB.getName());
        Assert.assertEquals(category.isDeleted(), category.isDeleted());
    }

    @Test
    public void readAllCategoryTest() {
        List<Category> expectedCategories = new ArrayList<>();
        Category category = new Category();
        category.setId(1);
        category.setName("Phone");

        Category category2 = new Category();
        category2.setId(2);
        category2.setName("Car");

        Category category3 = new Category();
        category3.setId(3);
        category3.setName("Plane");

        expectedCategories.add(category);
        expectedCategories.add(category2);
        expectedCategories.add(category3);


        List<Category> categoriesFromDB = categoryService.readAll();

        Assert.assertEquals(expectedCategories, categoriesFromDB);
//        Assert.assertEquals(category3.getName(), categoryFromDB.getName());
//        Assert.assertEquals(category3.isDeleted(), category3.isDeleted());
    }

}
