package com.z.shop.service;

import com.z.shop.entity.User;
import com.z.shop.entity.UserRole;
import com.z.shop.service.impl.UserServiceImpl;
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

@RunWith(JUnit4.class)
public class UserServiceTest {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceTest.class);
    private static DBManager dbManager;
    private static UserService userService;

    private static String SQL_DROP_DB = "DROP DATABASE If EXISTS z_shop_test;";
    private static String SQL_CRETE_DB = "CREATE DATABASE IF NOT EXISTS z_shop_test CHAR SET UTF8;";
    private static String SQL_USE_DB = "USE z_shop_test;";

    private static String SQL_CRETE_TABLE_USER = "CREATE TABLE user\n" +
            "(\n" +
            "    id        BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
            "    email     VARCHAR(50)        NOT NULL UNIQUE,\n" +
            "    first_name VARCHAR(25)        NOT NULL,\n" +
            "    last_name VARCHAR(25)        NOT NULL,\n" +
            "    password  VARCHAR(50)        NOT NULL,\n" +
            "    role      VARCHAR(20)        NOT NULL DEFAULT 'USER',\n" +
            "    amount    DECIMAL(9, 2) DEFAULT 0.00,\n" +
            "    blocked   BOOLEAN       DEFAULT FALSE\n" +
            ")";

    private static String SQL_BASIC_FILL_USER = "INSERT INTO user(email, first_name, last_name, password, role, amount)\n" +
            "value ('user@mail.com','user1','One User','user','USER',999.99)";

    @BeforeClass
    public static void setupClass() {
        dbManager = DBManager.getInstance();
        dbManager.setSqlConnectionUrl("jdbc:mysql://localhost:3306/z_shop_test?user=root&password=123456&serverTimezone=UTC&useSSL=false");
        userService = UserServiceImpl.getUserService();
    }

    @Before
    public void before() {
        try (Connection connection = dbManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL_DROP_DB);
            statement.executeUpdate(SQL_CRETE_DB);
            statement.executeUpdate(SQL_USE_DB);
            statement.executeUpdate(SQL_CRETE_TABLE_USER);
            statement.executeUpdate(SQL_BASIC_FILL_USER);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Test
    public void createUserTest() {
        User user = new User();
        user.setEmail("admin@mail.com");
        user.setFirstName("admin1");
        user.setLastName("ADMIN");
        user.setPassword("admin");
        user.setRole(UserRole.ADMIN);
        user.setAmount(0.0);

        userService.create(user);
        User userFromDB = userService.read(user.getId());
        Assert.assertEquals(user, userFromDB);

    }

    @Test
    public void readUserByIdTest() {
        User user = new User();
        user.setEmail("admin@mail.com");
        user.setFirstName("admin1");
        user.setLastName("ADMIN");
        user.setPassword("admin");
        user.setRole(UserRole.ADMIN);
        user.setAmount(0.0);

        userService.create(user);
        User userFromDB = userService.read(2);
        Assert.assertEquals(user, userFromDB);

    }

    @Test
    public void readUserByEmailTest() {
        User user = new User();
        user.setEmail("admin@mail.com");
        user.setFirstName("admin1");
        user.setLastName("ADMIN");
        user.setPassword("admin");
        user.setRole(UserRole.ADMIN);
        user.setAmount(0.0);

        userService.create(user);
        User userFromDB = userService.getUserByEmail("admin@mail.com");
        Assert.assertEquals(user, userFromDB);

    }

}
