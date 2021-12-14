package com.z.shop.service;

import com.z.shop.entity.Category;
import com.z.shop.service.impl.CategoryServiceImpl;
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
import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class CategoryServiceTest {
    private static final Logger LOGGER = LogManager.getLogger(CategoryServiceTest.class);
    private static DBManager dbManager;
    private static CategoryService categoryService;


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

    private static String SQL_BASIC_FILL_LANG = " INSERT INTO language (short_name, full_name) VALUE ('en','English'),('ua', 'Українська'), ('pl', 'Polska');";
    private static String SQL_BASIC_FILL_CAT = "INSERT INTO category() VALUE (), ();";
    private static String SQL_BASIC_FILL_CAT_DESCR = " INSERT INTO category_description(category_id, language_id, category_name) value (1, 'en', 'Phone'), (2, 'en', 'Car'), (1, 'ua', 'Телефон'), (1, 'pl', 'Telefon'), (2, 'ua', 'Авто'), (2, 'pl', 'Auto');";


    @BeforeClass
    public static void setupClass() {
        dbManager = DBManager.getInstance();
        dbManager.setSqlConnectionUrl("jdbc:mysql://localhost:3306/z_shop_test?user=root&password=123456&serverTimezone=UTC&useSSL=false");
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
            statement.executeUpdate(SQL_BASIC_FILL_LANG);
            statement.executeUpdate(SQL_BASIC_FILL_CAT);
            statement.executeUpdate(SQL_BASIC_FILL_CAT_DESCR);

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }


    @Test
    public void createCategoryTest() {
        Category category = new Category();
        category.setId(10);
        category.getTranslations().put("en", "Plane");
        category.getTranslations().put("ua", "Літак");
        category.getTranslations().put("pl", "Samolot");
        category.setDeleted(false);

        categoryService.create(category);

        Category categoryFromDB = categoryService.read(category.getId());

        Assert.assertEquals(category.getId(), categoryFromDB.getId());
        Assert.assertEquals(category.getTranslations(), categoryFromDB.getTranslations());
        Assert.assertEquals(category.isDeleted(), category.isDeleted());
    }

    @Test
    public void readAllCategoryTest() {
        List<Category> expectedCategories = new ArrayList<>();

        Category category = new Category();
        category.setId(1);
        category.getTranslations().put("en", "Phone");
        category.getTranslations().put("ua", "Телефон");
        category.getTranslations().put("pl", "Telefon");
        category.setDeleted(false);

        Category category2 = new Category();
        category2.setId(2);
        category2.getTranslations().put("en", "Car");
        category2.getTranslations().put("ua", "Авто");
        category2.getTranslations().put("pl", "Auto");
        category2.setDeleted(false);

        expectedCategories.add(category);
        expectedCategories.add(category2);

        List<Category> categoriesFromDB = categoryService.readAll();

        Assert.assertEquals(expectedCategories, categoriesFromDB);
    }
}
