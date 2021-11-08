package com.z.shop.utils;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionManager {
    private static final Logger logger = Logger.getLogger(ConnectionManager.class.getName());
    private static ConnectionManager connectionManager;
    private static String connectionUrl = null;


    private ConnectionManager() {
        try (InputStream input = new FileInputStream("app.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            connectionUrl = prop.getProperty("connection.url");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public static ConnectionManager getInstance(){
        if (connectionManager == null){
            connectionManager = new ConnectionManager();
        }
        return  connectionManager;
    }

    public static Connection getConnection() throws SQLException {
        ConnectionManager.getInstance();
        return  DriverManager.getConnection(connectionUrl);
    }

}
