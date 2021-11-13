package com.z.shop.utils;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBManager {
    public static final String URL_TO_IMAGE_FOLDER = "D:/Zenyk/EPAM/Final_Project/Z_Shop/src/main/webapp/image";
    private static  String SQL_CONNECTION_URL = "jdbc:mysql://localhost:3306/z_shop?user=root&password=123456&serverTimezone=UTC&useSSL=false";

    private static final Logger logger = LogManager.getLogger(DBManager.class.getName());
    private static DBManager dbManager;

    private DBManager() {
    }


    public static synchronized DBManager getInstance(){
        if (dbManager == null){
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public Connection getConnection() throws SQLException {
//        Connection con = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Context initContext = new InitialContext();
//            Context envContext  = (Context)initContext.lookup("java:/comp/env");
//
//            DataSource ds = (DataSource)envContext.lookup("jdbc/appname");
//            con = ds.getConnection();
//        } catch (Exception ex) {
//            logger.error("Cannot obtain a connection from the pool", ex);
//        }
//        return con;

        MysqlDataSource ds = new MysqlConnectionPoolDataSource();
        ds.setUrl(SQL_CONNECTION_URL);

        return ds.getConnection();
    }

    public static void close(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }



//    public static Connection getConnectionForTest() throws SQLException {
//        DBManager.getInstance();
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return  DriverManager.getConnection("jdbc:mysql://localhost:3306/z_shop_test?user=root&password=123456&serverTimezone=UTC&useSSL=false");
//    }

    public static void setSqlConnectionUrl(String sqlConnectionUrl) {
        SQL_CONNECTION_URL = sqlConnectionUrl;
    }

}
