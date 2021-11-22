package com.z.shop.dao.impl;

import com.z.shop.dao.UserDao;
import com.z.shop.entity.User;
import com.z.shop.entity.UserRole;
import com.z.shop.utils.DBManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    private static final Lock CONNECTION_LOCK = new ReentrantLock();
    private static  DBManager dbManager = DBManager.getInstance();


    private static final String READ_ALL = "SELECT * FROM user";
    private static final String CREATE = "INSERT INTO user (email, first_name, last_name, password, role, amount, blocked) VALUES (?,?,?,?,?,?,?)";
    private static final String READ_BY_ID = "SELECT * FROM user WHERE id =?";
    private static final String READ_BY_EMAIL = "SELECT * FROM user WHERE email =?";
    private static final String UPDATE_BY_ID = "UPDATE user SET email =?, first_name = ?, last_name = ?, role =?, password =?, amount=?, blocked=? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM user WHERE id =?";

    @Override
    public User create(User user) {
        try ( Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)){

            int k = 0;
            preparedStatement.setString(++k, user.getEmail());
            preparedStatement.setString(++k, user.getFirstName());
            preparedStatement.setString(++k, user.getLastName());
            preparedStatement.setString(++k, user.getPassword());
            preparedStatement.setString(++k, user.getRole().toString());
            preparedStatement.setDouble(++k, user.getAmount());
            preparedStatement.setBoolean(++k, user.isBlocked());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(1));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public User read(Integer id) {
        User user = null;
//        DBManager dbManager = DBManager.getInstance();
        try ( Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID)){

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            user = getUserFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public User update(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        CONNECTION_LOCK.lock();
//        DBManager dbManager = DBManager.getInstance();
        try {connection = dbManager.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
//            UPDATE user SET email =?, name = ?, last_name = ?, role =?, password =?, amount=?, blocked=? WHERE id = ?";
            int k = 0;
            preparedStatement.setString(++k, user.getEmail());
            preparedStatement.setString(++k, user.getFirstName());
            preparedStatement.setString(++k, user.getLastName());
            preparedStatement.setString(++k, user.getRole().toString());
            preparedStatement.setString(++k, user.getPassword());
            preparedStatement.setDouble(++k,user.getAmount());
            preparedStatement.setBoolean(++k, user.isBlocked());
            preparedStatement.setInt(++k, user.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.error(e);
                }
            }
            LOGGER.error(e);
        } finally {
            DBManager.close(preparedStatement);
            DBManager.close(connection);
            CONNECTION_LOCK.unlock();
        }
        return user;
    }

    @Override
    public void delete(Integer id) {
//        DBManager dbManager = DBManager.getInstance();
        try ( Connection connection = dbManager.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public List<User> readAll() {
        List<User> userRecords = new ArrayList<>();
//        DBManager dbManager = DBManager.getInstance();
        try ( Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()){

                userRecords.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return userRecords;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
//        DBManager dbManager = DBManager.getInstance();
        try ( Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_EMAIL)){

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            user = getUserFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName( resultSet.getString("last_name"));
        user.setRole(UserRole.valueOf(resultSet.getString("role")));
        user.setPassword(resultSet.getString("password"));
        user.setAmount(resultSet.getDouble("amount"));
        user.setBlocked(resultSet.getBoolean("blocked"));

        return user;
    }


}
