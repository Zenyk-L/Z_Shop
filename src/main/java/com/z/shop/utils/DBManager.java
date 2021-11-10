package com.z.shop.utils;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBManager {
    public static final String URL_TO_IMAGE_FOLDER = "D:/Zenyk/EPAM/Final_Project/Z_Shop/src/main/webapp/image";
    private static final String SQL_CONNECTION_URL = "jdbc:mysql://localhost:3306/z_shop?user=root&password=123456&serverTimezone=UTC&useSSL=false";

    private static final Logger logger = Logger.getLogger(DBManager.class.getName());
    private static DBManager dbManager;




    private DBManager() {
    }


    public static synchronized DBManager getInstance(){
        if (dbManager == null){
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public static Connection getConnection() throws SQLException {
//        Connection con = null;
//        try {
//            Context initContext = new InitialContext();
//            Context envContext  = (Context)initContext.lookup("java:/comp/env");
//
//            DataSource ds = (DataSource)envContext.lookup("jdbc/appname");
//            con = ds.getConnection();
//        } catch (NamingException ex) {
//            logger.error("Cannot obtain a connection from the pool", ex);
//        }
//        return con;

        MysqlDataSource ds = new MysqlConnectionPoolDataSource();
        ds.setUrl(SQL_CONNECTION_URL);

        return ds.getConnection();
    }



//    public static Connection getConnection() throws SQLException {
//        DBManager.getInstance();
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return  DriverManager.getConnection("jdbc:mysql://localhost:3306/z_shop?user=root&password=123456&serverTimezone=UTC&useSSL=false");
//    }

}
